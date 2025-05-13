package com.deloitte.day7_bootcamp.domain.repository;

import com.deloitte.day7_bootcamp.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //validar valor mínimo e calcular valor total
}
