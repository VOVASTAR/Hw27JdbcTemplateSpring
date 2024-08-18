package edu.hillel.hw27JdbcTemplateSpring.controller;

import edu.hillel.hw27JdbcTemplateSpring.entity.Product;
import edu.hillel.hw27JdbcTemplateSpring.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get-products")
    public List<Product> getProducts() {
        return productService.getAllProduct();
    }

    @PostMapping("/add")
    public Product addProduct(
            @RequestParam("id") Long id,
            @RequestParam("productName") String productName,
            @RequestParam("productPrice") Double productPrice) {
        return productService.addProduct(id, productName, productPrice);
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}
