package com.Ecommerce_Template;

import com.Ecommerce_Template.Controller.ProductRestController;
import com.Ecommerce_Template.Models.Product;
import com.Ecommerce_Template.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductRestController productRestController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productRestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void agregarProducto() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100.00));

        doNothing().when(productService).crearProducto(any(Product.class));

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto creado con éxito!"));
    }

    @Test
    void verProductos() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Test Product 1");
        product1.setPrice(BigDecimal.valueOf(100.00));

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Test Product 2");
        product2.setPrice(BigDecimal.valueOf(200.00));

        List<Product> products = Arrays.asList(product1, product2);

        when(productService.verProductos()).thenReturn(products);

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Test Product 1"))
                .andExpect(jsonPath("$[1].name").value("Test Product 2"));
    }

    @Test
    void borrarProducto() throws Exception {
        doNothing().when(productService).borrarProducto(1L);

        mockMvc.perform(delete("/api/productos/borrar_producto/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarProducto() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100.00));

        when(productService.buscarProducto(1L)).thenReturn(product);

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }
}
