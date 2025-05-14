package com.deloitte.day7_bootcamp.domain.repository;

import com.deloitte.day7_bootcamp.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
