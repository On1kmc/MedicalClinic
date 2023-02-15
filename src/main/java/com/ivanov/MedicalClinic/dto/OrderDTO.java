package com.ivanov.MedicalClinic.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private List<AnalyzeDTO> analyzeList;
}
