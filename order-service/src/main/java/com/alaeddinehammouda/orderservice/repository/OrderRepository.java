package com.alaeddinehammouda.orderservice.repository;

import com.alaeddinehammouda.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
