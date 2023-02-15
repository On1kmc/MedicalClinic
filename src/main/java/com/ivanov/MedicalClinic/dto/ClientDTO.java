package com.ivanov.MedicalClinic.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClientDTO {

    private int id;

    private String name;

    private String surname;

    private Date dateOfBirth;
}
