package com.ivanov.MedicalClinic.services;

import com.ivanov.MedicalClinic.Repo.ClientRepo;
import com.ivanov.MedicalClinic.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }


    public void saveOwner(Client client) {
        clientRepo.save(client);
    }

    public void setBirth(Client client, String birth) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(birth);
        Date date = simpleDateFormat.parse(birth);
        client.setDateOfBirth(date);
    }

    public List<Client> loadClients() {
        return clientRepo.findAll();
    }
}
