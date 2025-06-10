package com.Ecommerce_Template.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce_Template.Models.Compra;
import com.Ecommerce_Template.Models.CompraItem;
import com.Ecommerce_Template.Models.Product;
import com.Ecommerce_Template.Repository.CompraRepository;

import jakarta.transaction.Transactional;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProductService productService;

    public List<Compra> verCompras() {
        return compraRepository.findAll();
    }

    public Compra obtenerPorId(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    public void crearCompra(Compra c) {
        //Reduzco el stock de los productos comprados
        //Aca puedo pedirle al frontend que mande todos los datos del producto o hacerlo yo mismo, opto por la ultima
        for (CompraItem ci : c.getItems()) {
            Long productId = ci.getProduct().getId();
            Product product = productService.buscarProducto(productId);
            ci.setProduct(product);
            ci.getProduct().setStock(ci.getProduct().getStock() - ci.getQuantity());
            ci.setCompra(c);
        }
        compraRepository.save(c);
    }

    @Transactional
    public void borrarCompra(Long id) {
        compraRepository.deleteById(id);
    }

    public Compra buscarCompra(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Transactional
    public void actualizarCompra(Compra c) {
        //TODO update the stock of the products
        compraRepository.save(c);
    }

}
