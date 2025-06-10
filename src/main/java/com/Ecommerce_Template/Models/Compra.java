package com.Ecommerce_Template.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaCompra;
    private BigDecimal totalAmount;
    private String email;
    private String shippingAddress;
    private String numeroCelular;
    private String estado; // "PENDIENTE","ENTREGADO", "CANCELADO"

    /* No hay autenticación de usuarios
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/

    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraItem> items;


}


