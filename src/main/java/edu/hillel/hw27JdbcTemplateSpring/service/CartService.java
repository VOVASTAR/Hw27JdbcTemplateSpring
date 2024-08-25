package edu.hillel.hw27JdbcTemplateSpring.service;

import edu.hillel.hw27JdbcTemplateSpring.entity.Cart;
import edu.hillel.hw27JdbcTemplateSpring.repository.cart.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public void addCart() {
        cartRepository.addCart();
    }

    @Transactional
    public Cart getProductInCartById(Long cartId) {
        List<Map.Entry<Long, Integer>> cartProductMapEntry = cartRepository.getProductInCartById(cartId);
        return getCartProducts(cartId, cartProductMapEntry);
    }

    @Transactional
    public Cart addProductToCart(Long cartId, Long productId, Integer quantity) {
        cartRepository.addProductToCart(cartId, productId, quantity);
        List<Map.Entry<Long, Integer>> cartProductMapEntry = cartRepository.getProductInCartById(cartId);
        return getCartProducts(cartId, cartProductMapEntry);
    }

    private Cart getCartProducts(Long cartId, List<Map.Entry<Long, Integer>> cartProductMapEntry) {
        Cart cartById = cartRepository.getCartById(cartId);
        Map<Long, Integer> cartProductMap = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : cartProductMapEntry) {
            cartProductMap.put(entry.getKey(), entry.getValue());
        }
        cartById.setCartProduct(cartProductMap);
        return cartById;
    }

    public void deleteProductFromCart(Long cartId, Long productID) {
        cartRepository.deleteProductFromCart(cartId, productID);
    }

}
