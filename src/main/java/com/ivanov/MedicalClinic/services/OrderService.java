package com.ivanov.MedicalClinic.services;

import com.ivanov.MedicalClinic.Repo.OrderRepo;
import com.ivanov.MedicalClinic.dto.OrderDTOToJSON;
import com.ivanov.MedicalClinic.mapper.OrderMapper;
import com.ivanov.MedicalClinic.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepo orderRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
    }

    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

    public OrderDTOToJSON toOrderDTOToJSON(Order order) {
        return orderMapper.toJSONDTO(order);
    }
}
