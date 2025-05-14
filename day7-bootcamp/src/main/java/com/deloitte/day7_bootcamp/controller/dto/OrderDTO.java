package com.deloitte.day7_bootcamp.controller.dto;

import com.deloitte.day7_bootcamp.domain.model.Client;
import com.deloitte.day7_bootcamp.domain.model.Order;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class OrderDTO {
    @Positive
    @Min(value = 1)
    private double price;
    @Positive
    @Min(value = 20)
    private double totalAmount;

    public Order toEntity(){
        Order order = new Order();
        order.setPrice(this.getPrice());
        order.setTotalAmount(this.getTotalAmount());
        return order;
    }

    public static OrderDTO fromEntity(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setPrice(order.getPrice());
        dto.setTotalAmount(order.getTotalAmount());
        return dto;
    }
}
