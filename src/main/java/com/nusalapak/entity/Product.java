package com.nusalapak.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products", indexes = {
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_price", columnList = "price")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, columnDefinition = "varchar(200)")
    private String name;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double price;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private int quantity;

}
