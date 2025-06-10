package com.Ecommerce_Template.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce_Template.Models.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {}

