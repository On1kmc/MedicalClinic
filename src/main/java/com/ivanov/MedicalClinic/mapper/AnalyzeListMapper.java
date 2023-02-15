package com.ivanov.MedicalClinic.mapper;

import com.ivanov.MedicalClinic.dto.AnalyzeDTO;
import com.ivanov.MedicalClinic.model.Tests.Analyze;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AnalyzeMapper.class)
public interface AnalyzeListMapper {

    List<AnalyzeDTO> toDTOList(List<Analyze> analyzeList);

}
