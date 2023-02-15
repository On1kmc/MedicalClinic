package com.ivanov.MedicalClinic.services;

import com.ivanov.MedicalClinic.Repo.AnalyzeRepo;
import com.ivanov.MedicalClinic.dto.AnalyzeDTO;
import com.ivanov.MedicalClinic.mapper.AnalyzeListMapper;
import com.ivanov.MedicalClinic.model.Tests.Analyze;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyzeService {

    private final AnalyzeRepo analyzeRepo;

    private final AnalyzeListMapper analyzeListMapper;


    @Autowired
    public AnalyzeService(AnalyzeRepo analyzeRepo, AnalyzeListMapper analyzeListMapper) {
        this.analyzeRepo = analyzeRepo;
        this.analyzeListMapper = analyzeListMapper;
    }

    public List<Analyze> getAnalyzesById(int... id) {
        List<Integer> list = new ArrayList<>();
        for (int j : id) {
            list.add(j);
        }
        Iterable<Integer> iterable = new ArrayList<>(list);

        return analyzeRepo.findAllById(iterable);

    }

    public List<Analyze> findAll() {
        return analyzeRepo.findAll();
    }

    public List<AnalyzeDTO> toDTOList(List<Analyze> analyzeList) {
        return analyzeListMapper.toDTOList(analyzeList);
    }
}
