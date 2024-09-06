package org.example.service.serviceImpl;
import lombok.AllArgsConstructor;
import org.example.exception.NotFoundException;
import org.example.feinClient.CustomerServiceFeignClient;
import org.example.feinClient.ProductServiceFeignClient;
import org.example.model.Order;
import org.example.model.request.OrderRequest;
import org.example.model.response.ApiResponse;
import org.example.model.response.CustomerResponse;
import org.example.model.response.OrderResponse;
import org.example.model.response.ProductResponse;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerServiceFeignClient customerServiceFeignClient;
    private final ProductServiceFeignClient productServiceFeignClient;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        // 1. Fetch customer details
        CustomerResponse customerResponse;
        try {
            ResponseEntity<ApiResponse<CustomerResponse>> customerResponseEntity = customerServiceFeignClient.getCustomerById(orderRequest.getCustomerId());
            ApiResponse<CustomerResponse> customerResponseApiResponse = customerResponseEntity.getBody();
            if (customerResponseApiResponse == null) {
                throw new NotFoundException("Customer not found with id: " + orderRequest.getCustomerId());
            }
            customerResponse = customerResponseApiResponse.getPayload();
        } catch (Exception e) {
            throw new NotFoundException("Customer not found with id: " + orderRequest.getCustomerId());
        }

        // 2. Fetch product details for each product ID
        List<ProductResponse> productResponses = orderRequest.getProductIds().stream()
                .map(productId -> {
                    try {
                        ResponseEntity<ApiResponse<ProductResponse>> productResponseEntity = productServiceFeignClient.getProductById(productId);
                        ApiResponse<ProductResponse> productResponseApi = productResponseEntity.getBody();
                        if (productResponseApi == null) {
                            throw new NotFoundException("Product not found with id: " + productId);
                        }
                        return productResponseApi.getPayload();
                    } catch (Exception e) {
                        throw new NotFoundException("Product not found with id: " + productId);
                    }
                }).collect(Collectors.toList());

        // 3. Create OrderResponse object and set customer details and product details
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setCustomerResponse(customerResponse);
        orderResponse.setProductResponses(productResponses);
        orderResponse.setOrderDate(orderRequest.getOrderDate());

        // 4. Create and save the order with product IDs
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        order.setOrderDate(orderRequest.getOrderDate());
        Order savedOrder = orderRepository.save(order);
        orderResponse.setId(savedOrder.getId());
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            ResponseEntity<ApiResponse<CustomerResponse>> customerResponseEntity = customerServiceFeignClient.getCustomerById(order.getCustomerId());
            orderResponse.setCustomerResponse(Objects.requireNonNull(customerResponseEntity.getBody()).getPayload());
            List<Long> productIds = order.getProductIds();
            List<ProductResponse> productResponses = productIds.stream().map(productId -> {
                ResponseEntity<ApiResponse<ProductResponse>> productResponseEntity = productServiceFeignClient.getProductById(productId);
                return Objects.requireNonNull(productResponseEntity.getBody()).getPayload();
            }).toList();
            orderResponse.setProductResponses(productResponses);
            orderResponse.setOrderDate(order.getOrderDate());
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }



    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Order not found with id: " + id)
        );
        CustomerResponse customerResponse;
        try {
            ResponseEntity<ApiResponse<CustomerResponse>> customerResponseEntity = customerServiceFeignClient.getCustomerById(order.getCustomerId());
            ApiResponse<CustomerResponse> customerResponseApiResponse = customerResponseEntity.getBody();
            if (customerResponseApiResponse == null) {
                throw new NotFoundException("Customer not found with id: " + order.getCustomerId());
            }
            customerResponse = customerResponseApiResponse.getPayload();
        } catch (Exception e) {
            throw new NotFoundException("Customer not found with id: " + order.getCustomerId());
        }
        List<ProductResponse> productResponses = order.getProductIds().stream()
                .map(productId -> {
                    try {
                        ResponseEntity<ApiResponse<ProductResponse>> productResponseEntity = productServiceFeignClient.getProductById(productId);
                        ApiResponse<ProductResponse> productResponseApi = productResponseEntity.getBody();
                        if (productResponseApi == null) {
                            throw new NotFoundException("Product not found with id: " + productId);
                        }
                        return productResponseApi.getPayload();
                    } catch (Exception e) {
                        throw new NotFoundException("Product not found with id: " + productId);
                    }
                }).collect(Collectors.toList());
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomerResponse(customerResponse);
        orderResponse.setProductResponses(productResponses);
        orderResponse.setOrderDate(order.getOrderDate());
        return orderResponse;
    }

    @Override
    public OrderResponse updateOrderById(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Order not found with id: " + id)
        );
        CustomerResponse customerResponse;
        try {
            ResponseEntity<ApiResponse<CustomerResponse>> customerResponseEntity = customerServiceFeignClient.getCustomerById(orderRequest.getCustomerId());
            ApiResponse<CustomerResponse> customerResponseApiResponse = customerResponseEntity.getBody();
            if (customerResponseApiResponse == null) {
                throw new NotFoundException("Customer not found with id: " + orderRequest.getCustomerId());
            }
            customerResponse = customerResponseApiResponse.getPayload();
        } catch (Exception e) {
            throw new NotFoundException("Customer not found with id: " + orderRequest.getCustomerId());
        }
        List<ProductResponse> productResponses = orderRequest.getProductIds().stream()
                .map(productId -> {
                    try {
                        ResponseEntity<ApiResponse<ProductResponse>> productResponseEntity = productServiceFeignClient.getProductById(productId);
                        ApiResponse<ProductResponse> productResponseApi = productResponseEntity.getBody();
                        if (productResponseApi == null) {
                            throw new NotFoundException("Product not found with id: " + productId);
                        }
                        return productResponseApi.getPayload();
                    } catch (Exception e) {
                        throw new NotFoundException("Product not found with id: " + productId);
                    }
                }).collect(Collectors.toList());

        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        order.setOrderDate(orderRequest.getOrderDate());

        Order updatedOrder = orderRepository.save(order);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(updatedOrder.getId());
        orderResponse.setCustomerResponse(customerResponse);
        orderResponse.setProductResponses(productResponses);
        orderResponse.setOrderDate(updatedOrder.getOrderDate());
        return orderResponse;
    }

    @Override
    public void deleteOrderById(Long id) {
        if (orderRepository.findById(id).isEmpty()){
            throw new NotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }


}
