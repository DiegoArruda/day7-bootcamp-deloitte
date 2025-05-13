package com.deloitte.day7_bootcamp.service.impl;

import com.deloitte.day7_bootcamp.domain.model.Client;
import com.deloitte.day7_bootcamp.domain.model.Order;
import com.deloitte.day7_bootcamp.domain.repository.OrderRepository;
import com.deloitte.day7_bootcamp.service.OrderService;

import java.util.List;
import java.util.NoSuchElementException;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Order not found with id " + id));
    }

    @Override
    public Order create(Order order) {
        //validar valor minimo e total aqui
        if (order.getId() != null && orderRepository.existsById(order.getId())) {
            throw new IllegalArgumentException("Order with id " + order.getId() + " already exists");
        }
        
        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, Order order) {
        //validar valor minimo e total aqui
        Order existingOrder = findById(id);
        return orderRepository.save(existingOrder);
    }

    @Override
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NoSuchElementException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}
