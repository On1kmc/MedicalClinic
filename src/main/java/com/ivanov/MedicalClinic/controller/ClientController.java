package com.ivanov.MedicalClinic.controller;

import com.ivanov.MedicalClinic.dto.ClientDTO;
import com.ivanov.MedicalClinic.dto.OrderDTO;
import com.ivanov.MedicalClinic.model.Client;
import com.ivanov.MedicalClinic.services.ClientService;
import com.ivanov.MedicalClinic.services.OrderService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class ClientController {

    private final ClientService clientService;

    private final OrderService orderService;



    @Autowired
    public ClientController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping
    public String owners(Model model) {
        List<Client> clientList = clientService.loadClients();
        List<ClientDTO> clientDTOList = clientService.toClientDTOList(clientList);
        model.addAttribute("clients", clientDTOList);
        return "owners";
    }


    @GetMapping("/new")
    public String newOwner(@ModelAttribute("client") ClientDTO clientDTO) {
        return "new-owner";
    }

    @PostMapping("/new")
    public String addOwner(@ModelAttribute("client") @Valid ClientDTO clientDTO, @RequestParam("birth") String birth, BindingResult bindingResult) {
        Client client = clientService.toClient(clientDTO);
        try {
            clientService.setBirth(client, birth);
        } catch (ParseException e) {
            bindingResult.rejectValue("dateOfBirth", "", "Неверная дата");
        }
        if (bindingResult.hasErrors()) {
            return "new-owner";
        }
        clientService.saveOwner(client);
        return "redirect:/owner";
    }

    @GetMapping("/{id}")
    public String ownerPage(@PathVariable("id") int id, Model model) {
        ClientDTO client = clientService.toClientDTO(clientService.findClientById(id));
        List<OrderDTO> orderList = orderService.getOrderListForClient(id);
        model.addAttribute("client", client);
        model.addAttribute("orderList", orderList);
        return "owner-page";
    }

}
