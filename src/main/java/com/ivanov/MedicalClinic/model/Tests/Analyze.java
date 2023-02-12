package com.ivanov.MedicalClinic.model.Tests;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Analyzes")
public class Analyze {

    String name;
    String description;

    @Id
    private Integer id;



}
