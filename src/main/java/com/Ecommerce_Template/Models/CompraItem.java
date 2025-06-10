package com.Ecommerce_Template.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.JoinColumn;


import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "compra_items")
public class CompraItem {

    @Id
    @GeneratedValue
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;
}