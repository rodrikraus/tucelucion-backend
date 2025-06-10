package com.Ecommerce_Template.Controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce_Template.Models.Compra;
import com.Ecommerce_Template.Models.CompraItem;
import com.Ecommerce_Template.Service.CompraService;
import com.Ecommerce_Template.Service.ProductService;

@RestController
@RequestMapping("/api/compras")
public class CompraRestController {
    @Autowired
    private CompraService compraService;
    @Autowired
    private ProductService productService;

    @PostMapping
    public String agregarCompra(@RequestBody Compra c) {
        Compra compra = new Compra();
        compra.setEmail(c.getEmail());
        compra.setFechaCompra(c.getFechaCompra());
        compra.setId(c.getId());
        compra.setItems(c.getItems());
        compra.setShippingAddress(c.getShippingAddress());
        compra.setNumeroCelular(c.getNumeroCelular());
        compra.setEstado("PENDIENTE");
        BigDecimal total = new BigDecimal("0");
        for(CompraItem i : c.getItems()) {
            BigDecimal precioProducto = productService.buscarProducto(i.getProduct().getId()).getPrice();
            total = total.add(precioProducto);
        }
        compra.setTotalAmount(total);
        compraService.crearCompra(compra);
        return "Compra creada con éxito!";
    }
}
