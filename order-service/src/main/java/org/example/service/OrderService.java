package org.example.service;
import org.example.model.request.OrderRequest;
import org.example.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder (OrderRequest orderRequest);
    List<OrderResponse> getAllOrders ();
    OrderResponse getOrderById (Long id);
    OrderResponse updateOrderById (Long id, OrderRequest orderRequest);
    void deleteOrderById (Long id);
}
