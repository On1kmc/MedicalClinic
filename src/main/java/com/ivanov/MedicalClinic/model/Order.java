package com.ivanov.MedicalClinic.model;

import com.ivanov.MedicalClinic.model.Tests.Analyze;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToMany
    @JoinColumn(name = "analyze_id", referencedColumnName = "id")
    private List<Analyze> analyzeList;

}
