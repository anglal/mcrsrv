package com.zlimbu.mcrsr1.repos;

import com.zlimbu.mcrsr1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
