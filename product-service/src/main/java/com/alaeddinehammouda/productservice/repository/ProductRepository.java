package com.alaeddinehammouda.productservice.repository;

import com.alaeddinehammouda.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
