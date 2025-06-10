package com.Ecommerce_Template.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Ecommerce_Template.Models.Product;
import com.Ecommerce_Template.Service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String verProductos(Model model) {
        List<Product> listaProductos = productService.verProductos();
        model.addAttribute("productos", listaProductos);
        return "listadoProductos.html";
    }

    @GetMapping("/agregarProducto")
    public String agregarProductoForm(Model model) {
        model.addAttribute("nuevoProducto", new Product());
        return "agregarProducto.html";
    }

    @PostMapping("/guardarProductoNuevo")
    public String agregarProducto(@ModelAttribute Product nuevoProducto) {
        productService.crearProducto(nuevoProducto);
        return "redirect:/productos";
    }

    @GetMapping("/modificarProducto/{id}")
    public String modificarProducto(@PathVariable long id, Model model) {
        Product producto = productService.buscarProducto(id);
        model.addAttribute("producto", producto);
        return "modificarProducto.html";
    }

    @PostMapping("guardarModificacionProducto")
    public String guardarModificacionProducto(@ModelAttribute Product producto) {
        productService.crearProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/borrar/{id}")
    public String borrarProducto(@PathVariable long id) {
        productService.borrarProducto(id);
        return "redirect:/productos";
    }



}