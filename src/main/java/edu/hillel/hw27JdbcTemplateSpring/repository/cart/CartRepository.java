package edu.hillel.hw27JdbcTemplateSpring.repository.cart;

import edu.hillel.hw27JdbcTemplateSpring.entity.Cart;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@Repository

@AllArgsConstructor
public class CartRepository {

    private final JdbcTemplate jdbcTemplate;

    public void addCart() {
        String query = "INSERT INTO carts DEFAULT VALUES";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> connection.prepareStatement(query, new String[]{"cart_id"}), keyHolder);
    }

    public Cart getCartById(Long cartId) {
        String queryForCart = "SELECT * FROM carts WHERE cart_id = ?";
        return jdbcTemplate.queryForObject(queryForCart, new BeanPropertyRowMapper<>(Cart.class), cartId);

    }

    public List<Map.Entry<Long, Integer>> getProductInCartById(Long cartId) {
        String queryForProducts = "SELECT * FROM cart_product WHERE cart_id = ?";
        return jdbcTemplate.query(queryForProducts, new Object[]{cartId}, cartProductRowMapper);
    }

    private final RowMapper<Map.Entry<Long, Integer>> cartProductRowMapper = (rs, rowNum) -> {
        Long productId = rs.getLong("product_id");
        Integer quantity = rs.getInt("quantity");
        return new AbstractMap.SimpleEntry<>(productId, quantity);
    };

    public void addProductToCart(Long cartId, Long productId, Integer quantity) {
        String queryForProducts = "INSERT INTO cart_product (cart_id, product_id, quantity) VALUES (?, ?, ?) " +
                "ON CONFLICT (cart_id, product_id) DO UPDATE SET quantity = EXCLUDED.quantity + cart_product.quantity";
        jdbcTemplate.update(queryForProducts, cartId, productId, quantity);
    }

    public void deleteProductFromCart(Long cartId, Long productId) {
        String query = "DELETE FROM cart_product WHERE cart_id =? AND product_id = ?";
        jdbcTemplate.update(query, cartId, productId);
    }

}
