package com.Ecommerce_Template.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce_Template.Models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
