package com.nusalapak.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    Payment payment;
    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    Courier courier;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "order_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double paymentPrice;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double shippingPrice;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double adminPrice;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;

}
