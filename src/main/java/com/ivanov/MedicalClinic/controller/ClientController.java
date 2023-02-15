package com.ivanov.MedicalClinic.controller;

import com.ivanov.MedicalClinic.dto.ClientDTO;
import com.ivanov.MedicalClinic.model.Client;
import com.ivanov.MedicalClinic.services.ClientService;
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



    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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
        clientService.saveOwner(client);
        return "redirect:/owner";
    }

}
