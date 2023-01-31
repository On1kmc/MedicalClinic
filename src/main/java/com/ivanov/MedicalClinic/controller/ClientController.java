package com.ivanov.MedicalClinic.controller;

import com.ivanov.MedicalClinic.model.Client;
import com.ivanov.MedicalClinic.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
        model.addAttribute("clients", clientList);
        return "owners";
    }


    @GetMapping("/new")
    public String newOwner(@ModelAttribute("client") Client client) {
        return "new-owner";
    }

    @PostMapping("/new")
    public String addOwner(@ModelAttribute("client") @Valid Client client, @RequestParam("birth") String birth, BindingResult bindingResult) {
        try {
            clientService.setBirth(client, birth);
        } catch (ParseException e) {
            bindingResult.rejectValue("dateOfBirth", "", "Неверная дата");
        }
        clientService.saveOwner(client);
        return "redirect:/owner";
    }

}
