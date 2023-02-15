package com.ivanov.MedicalClinic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanov.MedicalClinic.dto.AnalyzeDTO;
import com.ivanov.MedicalClinic.model.Client;
import com.ivanov.MedicalClinic.model.Order;
import com.ivanov.MedicalClinic.model.Tests.Analyze;
import com.ivanov.MedicalClinic.services.AnalyzeService;
import com.ivanov.MedicalClinic.services.ClientService;
import com.ivanov.MedicalClinic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final AnalyzeService analyzeService;

    private final ClientService clientService;

    private final OrderService orderService;



    @Autowired
    public OrderController(AnalyzeService analyzeService, ClientService clientService, OrderService orderService) {
        this.analyzeService = analyzeService;
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping("/new")
    public String newOrder(@RequestParam("id") int id, Model model) {
        model.addAttribute("person_id", id);
        List<Analyze> analyzes = analyzeService.findAll();
        List<AnalyzeDTO> analyzeDTOList = analyzeService.toDTOList(analyzes);
        model.addAttribute("analyzes", analyzeDTOList);
        return "new-order";
    }

    @PostMapping("/new")
    public String addOrder(@RequestParam("analyze1") int id1, @RequestParam("analyze2") int id2, @RequestParam("client_id") int client_id) {
        Order order = new Order();
        List<Analyze> analyzes = analyzeService.getAnalyzesById(id1, id2);
        order.setAnalyzeList(analyzes);
        Client client = clientService.getClientById(client_id);
        order.setClient(client);
        orderService.saveOrder(order);

        ObjectMapper mapper = new ObjectMapper();
        String s;
        try {
            s = mapper.writeValueAsString(orderService.toOrderDTOToJSON(order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/new-order";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(s, headers);
        restTemplate.postForObject(url, request, String.class);
        return "redirect:/order/new";
    }

}
