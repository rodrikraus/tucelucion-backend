package com.Ecommerce_Template.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;
    private BigDecimal price;
    private Integer stock;
    @Column(length = 1024)
    private String description;
    @Column(length = 512)
    private String imageUrl;
    @Column(length = 256)
    private String tags;
    private String productCategory;
}
