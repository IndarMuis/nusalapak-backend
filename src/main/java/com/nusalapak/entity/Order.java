package com.nusalapak.entity;

import com.nusalapak.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

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

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    Payment payment;

    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    Courier courier;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

//    @Type(value = "status_enum_type")

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

}
