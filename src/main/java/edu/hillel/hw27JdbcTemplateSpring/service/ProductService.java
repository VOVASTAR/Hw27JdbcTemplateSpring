package edu.hillel.hw27JdbcTemplateSpring.service;


import edu.hillel.hw27JdbcTemplateSpring.entity.Product;
import edu.hillel.hw27JdbcTemplateSpring.repository.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    public Product addProduct(Long id, String productName, Double productPrice) {
        Product newProduct = Product.builder()
                .productId(id)
                .productName(productName)
                .productPrice(productPrice)
                .build();
        productRepository.addProduct(newProduct);
        return newProduct;
    }

    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
