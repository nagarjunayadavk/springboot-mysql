package com.product.api.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.product.api.model.Product;

//public interface ProductDao extends JpaRepository<Product, Long> {
public interface ProductDao extends ElasticsearchRepository<Product, Long> {	
//	@Query("select p from Product p where p.name LIKE %?1%")
//	@Query("select u from Product u")
//	List<Product> findByNameTest(String name);
	List<Product> findAll();
}
