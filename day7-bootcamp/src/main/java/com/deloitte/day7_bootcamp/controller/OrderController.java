package com.deloitte.day7_bootcamp.controller;

import com.deloitte.day7_bootcamp.controller.dto.OrderDTO;
import com.deloitte.day7_bootcamp.domain.model.Order;
import com.deloitte.day7_bootcamp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<Order> orders = orderService.findAll();
        List<OrderDTO> ordersDTOs = orders.stream()
                .map(OrderDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ordersDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        Order order = orderService.findById(id);
        return ResponseEntity.ok(OrderDTO.fromEntity(order));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO orderDTO) {
        Order newOrder = orderDTO.toEntity();
        Order createdOrder = orderService.create(newOrder);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(OrderDTO.fromEntity(createdOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = orderDTO.toEntity();
        Order updatedOrder = orderService.update(id, order);
        return ResponseEntity.ok(OrderDTO.fromEntity(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
