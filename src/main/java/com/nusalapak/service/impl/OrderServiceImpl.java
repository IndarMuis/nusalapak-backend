package com.nusalapak.service.impl;

import com.nusalapak.dto.request.CreateOrderDetailsRequest;
import com.nusalapak.dto.request.CreateOrderRequest;
import com.nusalapak.entity.*;
import com.nusalapak.entity.enums.OrderStatus;
import com.nusalapak.repository.*;
import com.nusalapak.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final CourierRepository courierRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    @Override
    public void create(CreateOrderRequest request) {

        String customerEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findCustomerByAccount_Email(customerEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
        );

        Courier courier = courierRepository.findById(request.getCourierId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "courier not found")
        );

        Payment payment = paymentRepository.findById(request.getPaymentId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "payment not found")
        );

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .customer(customer)
                .courier(courier)
                .payment(payment)
                .paymentPrice(request.getPaymentPrice())
                .adminPrice(request.getAdminPrice())
                .shippingPrice(request.getShippingPrice())
                .totalPrice(request.getTotalPrice())
                .status(OrderStatus.PENDING)
                .build();
        orderRepository.save(order);

        for (CreateOrderDetailsRequest orderDetail : request.getOrderDetails()) {
            Product product = productRepository.findById(orderDetail.getProductId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product id " + orderDetail.getProductId() + " not found")
            );

            OrderDetail detail = OrderDetail.builder()
                    .order(order)
                    .product(product)
                    .seller(product.getSeller())
                    .quantity(orderDetail.getQuantity()).build();
            orderDetailRepository.save(detail);
        }

    }
}
