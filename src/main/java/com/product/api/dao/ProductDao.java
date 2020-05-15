package com.product.api.dao;

import org.springframework.data.repository.CrudRepository;
import com.product.api.model.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

}
