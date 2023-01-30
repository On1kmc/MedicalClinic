package com.example.MedicalClinic.controller;

import com.example.MedicalClinic.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class ClientController {

    @GetMapping
    public String owners(Model model) {
        List<Client> clientList = new ArrayList<>();
        Client client = new Client();
        client.setName("Andrey");
        client.setSurname("Ivanov");
        client.setDateOfBirth(new Date(1993, 1, 3));
        clientList.add(client);
        model.addAttribute("clients", clientList);
        return "owners";
    }


    @GetMapping("/new")
    public String newOwner(@ModelAttribute("client") Client client) {
        return "new-owner";
    }

}
