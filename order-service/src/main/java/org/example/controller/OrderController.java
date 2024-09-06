package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.example.model.request.OrderRequest;
import org.example.model.response.ApiResponse;
import org.example.model.response.OrderResponse;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @Operation(summary = "Create a new order")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .message("Order created successfully")
                .payload(orderResponse)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    @Operation(summary = "Get all orders")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrder(){
        List<OrderResponse> orderResponse = orderService.getAllOrders();
        ApiResponse<List<OrderResponse>> apiResponse = ApiResponse.<List<OrderResponse>>builder()
                .message("Get all orders successfully")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);

    }
    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.getOrderById(id);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .message("Get Order by id " + " successfully")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order with id")
    public ResponseEntity<ApiResponse<OrderResponse>> deleteOrder(@PathVariable Long id){
        orderService.deleteOrderById(id);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .message("Order deleted with id "+ id + " successfully")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update order with id")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.updateOrderById(id, orderRequest);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .message("Order updated with id " + id + " successfully")
               .payload(orderResponse)
               .status(HttpStatus.OK)
               .time(LocalDateTime.now())
               .build();
        return ResponseEntity.ok(apiResponse);
    }

}
