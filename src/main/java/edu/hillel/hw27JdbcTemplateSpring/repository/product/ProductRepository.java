package edu.hillel.hw27JdbcTemplateSpring.repository.product;

import edu.hillel.hw27JdbcTemplateSpring.entity.Product;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Product> getAllProduct() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, new ProductRowMapper());
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO products VALUES (?,?,?)";
        jdbcTemplate.update(query, product.getProductId(), product.getProductName(), product.getProductPrice());
    }

    public Product getProductById(Long id) {
        String query = "SELECT * FROM products WHERE product_id =?";
        return jdbcTemplate.queryForObject(query, new ProductRowMapper(), id);
    }

    public void deleteProduct(Long id) {
        String query = "DELETE FROM products WHERE product_id = ?";
        jdbcTemplate.update(query, id);
    }
}
