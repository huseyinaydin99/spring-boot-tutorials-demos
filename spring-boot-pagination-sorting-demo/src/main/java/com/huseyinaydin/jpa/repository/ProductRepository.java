package com.huseyinaydin.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huseyinaydin.jpa.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
