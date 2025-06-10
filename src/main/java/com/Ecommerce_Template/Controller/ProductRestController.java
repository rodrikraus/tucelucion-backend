package com.Ecommerce_Template.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce_Template.Models.Product;
import com.Ecommerce_Template.Service.ProductService;

@RestController
@RequestMapping("/api/productos")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public String agregarProducto(@RequestBody Product p) {
        productService.crearProducto(p);
        return "Producto creado con éxito!";
    }

    @GetMapping
    @ResponseBody
    public List<Product> verProductos() {
        return productService.verProductos();
    }

    @DeleteMapping("/borrar_producto/{id}")
    public void borrarProducto(@PathVariable Long id) {
        productService.borrarProducto(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product buscarProducto(@PathVariable Long id) {
        return productService.buscarProducto(id);
    }
}
