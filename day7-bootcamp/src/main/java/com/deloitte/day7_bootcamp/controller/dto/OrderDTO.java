package com.deloitte.day7_bootcamp.controller.dto;

import com.deloitte.day7_bootcamp.domain.model.Client;
import com.deloitte.day7_bootcamp.domain.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

   private double price;

    public Order toEntity(OrderDTO dto){
        Order order = new Order();
        order.setPrice(dto.getPrice());
        return order;
    }

    public static OrderDTO fromEntity(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setPrice(order.getPrice());
        return dto;
    }
}
