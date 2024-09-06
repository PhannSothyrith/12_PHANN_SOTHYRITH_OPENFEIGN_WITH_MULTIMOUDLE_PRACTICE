package org.example.repository;

import org.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
        SELECT o.product_ids
        FROM orders o
        WHERE o.customer_id = :customerId
         """,nativeQuery = true)
    List<Long> findProductIdByCustomerId(Long customerId);
}
