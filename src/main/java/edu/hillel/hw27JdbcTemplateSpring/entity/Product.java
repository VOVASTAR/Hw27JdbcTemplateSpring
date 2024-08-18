package edu.hillel.hw27JdbcTemplateSpring.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long productId;
    private String productName;
    private Double productPrice;
}
