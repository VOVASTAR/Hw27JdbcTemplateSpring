package edu.hillel.hw27JdbcTemplateSpring.repository.product;

import edu.hillel.hw27JdbcTemplateSpring.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .productId(rs.getLong("product_id"))
                .productName(rs.getString("product_name"))
                .productPrice(rs.getDouble("price"))
                .build();
    }
}
