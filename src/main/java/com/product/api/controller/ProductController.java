package com.product.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dao.ProductDao;
import com.product.api.model.Product;

@RestController
public class ProductController {
	@Autowired
    private ProductDao dao;
	
	@GetMapping("/products")
	public List < Product > getAllProducts(){
		return (List<Product>) dao.findAll();
	}
	
	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product product) {
        return dao.save(product);
    }
    
    @PutMapping ("/products/{id}")
    public ResponseEntity <Product> updateProduct(@PathVariable(value = "id") Long productId,
            @Valid @RequestBody Product productDetails) {

        
    	    Product product = dao.findById(productId)
    	    		.orElseThrow(() -> new IllegalStateException("Product Id not found :: " + productId));

	    	product.setName(productDetails.getName());
	    	product.setDescription(productDetails.getDescription());
	    	product.setPrice(productDetails.getPrice());
            final Product updateProduct = dao.save(product);
            return ResponseEntity.ok(updateProduct);
            
    }
    
    @DeleteMapping ("/products/{id}")
    public Map < String, Boolean > deleteProduct(@PathVariable(value = "id") Long productId) {

        
    	    Product product = dao.findById(productId)
    	    		.orElseThrow(() -> new IllegalStateException("Product Id not found :: " + productId));
//                -> new ResourceNotFoundException("Product Id not found :: " + productId));
    	    
            dao.delete(product);
            Map < String, Boolean > response = new HashMap < > ();
            response.put("deleted", Boolean.TRUE);
            return response;
            
    }
   
}
