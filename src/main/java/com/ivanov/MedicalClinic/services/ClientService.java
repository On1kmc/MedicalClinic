package com.ivanov.MedicalClinic.services;

import com.ivanov.MedicalClinic.Repo.ClientRepo;
import com.ivanov.MedicalClinic.dto.ClientDTO;
import com.ivanov.MedicalClinic.mapper.ClientListMapper;
import com.ivanov.MedicalClinic.mapper.ClientMapper;
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

    private final ClientListMapper clientListMapper;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepo clientRepo, ClientListMapper clientListMapper, ClientMapper clientMapper) {
        this.clientRepo = clientRepo;
        this.clientListMapper = clientListMapper;
        this.clientMapper = clientMapper;
    }


    public void saveOwner(Client client) {
        clientRepo.save(client);
    }

    public void setBirth(Client client, String birth) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(birth);
        client.setDateOfBirth(date);
    }

    public List<Client> loadClients() {
        return clientRepo.findAll();
    }

    public Client getClientById(int id) {
        return clientRepo.findById(id).get();
    }

    public List<ClientDTO> toClientDTOList(List<Client> clientList) {
        return clientListMapper.toDTOList(clientList);
    }

    public Client toClient(ClientDTO clientDTO) {
        return clientMapper.toModel(clientDTO);
    }
}
