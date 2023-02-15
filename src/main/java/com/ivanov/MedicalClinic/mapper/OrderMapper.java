package com.ivanov.MedicalClinic.mapper;

import com.ivanov.MedicalClinic.dto.OrderDTO;
import com.ivanov.MedicalClinic.dto.OrderDTOToJSON;
import com.ivanov.MedicalClinic.model.Order;
import com.ivanov.MedicalClinic.model.Tests.Analyze;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = AnalyzeMapper.class)
public interface OrderMapper {

    default OrderDTOToJSON toJSONDTO(Order order) {
        OrderDTOToJSON orderDTOToJSON = new OrderDTOToJSON();
        orderDTOToJSON.setId(order.getId());
        List<Integer> idsList = order.getAnalyzeList().stream().map(Analyze::getId).toList();
        orderDTOToJSON.setAnalyzeIds(idsList);
        return orderDTOToJSON;
    }
}
