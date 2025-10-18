package com.Ecommerce_Template;

import com.Ecommerce_Template.Controller.CompraRestController;
import com.Ecommerce_Template.Models.Compra;
import com.Ecommerce_Template.Models.CompraItem;
import com.Ecommerce_Template.Models.Product;
import com.Ecommerce_Template.Service.CompraService;
import com.Ecommerce_Template.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CompraRestControllerTest {

    @Mock
    private CompraService compraService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CompraRestController compraRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agregarCompra_shouldReturnSuccessMessage() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100));

        CompraItem item = new CompraItem();
        item.setProduct(product);
        item.setQuantity(1);

        Compra compra = new Compra();
        compra.setEmail("test@example.com");
        compra.setItems(Collections.singletonList(item));

        when(productService.buscarProducto(1L)).thenReturn(product);
        doNothing().when(compraService).crearCompra(any(Compra.class));

        // Act
        String response = compraRestController.agregarCompra(compra);

        // Assert
        assertEquals("Compra creada con éxito!", response);
        verify(compraService, times(1)).crearCompra(any(Compra.class));
    }
}
