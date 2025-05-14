package com.deloitte.day7_bootcamp.controller.dto;

import com.deloitte.day7_bootcamp.domain.model.Client;
import com.deloitte.day7_bootcamp.domain.model.Order;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive
    @Min(value = 1)
    private double price;
    @Positive
    @Min(value = 20)
    private double totalAmount;

    public Order toEntity(){
        Order order = new Order();
        order.setId(this.getId());
        order.setPrice(this.getPrice());
        order.setTotalAmount(this.getTotalAmount());
        return order;
    }

    public static OrderDTO fromEntity(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setPrice(order.getPrice());
        dto.setTotalAmount(order.getTotalAmount());
        return dto;
    }
}
