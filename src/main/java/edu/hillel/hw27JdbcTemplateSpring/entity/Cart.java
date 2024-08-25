package edu.hillel.hw27JdbcTemplateSpring.entity;

import jakarta.annotation.PostConstruct;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private Long cartId;
    private Map<Long, Integer> cartProduct;

    @PostConstruct
    private void init() {
        this.cartProduct = new HashMap<>();
    }
}
