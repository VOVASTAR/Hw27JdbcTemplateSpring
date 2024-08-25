package edu.hillel.hw27JdbcTemplateSpring.controller;

import edu.hillel.hw27JdbcTemplateSpring.entity.Cart;
import edu.hillel.hw27JdbcTemplateSpring.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("/cart/")
public class CartController {

    public CartService cartService;

    @PostMapping("/add-new-cart")
    public void addNewCart() {
        cartService.addCart();
    }

    @GetMapping("/{id}")
    public Cart getProductInCartById(@PathVariable Long id) {
        return cartService.getProductInCartById(id);
    }

    @PostMapping("/add-to-cart")
    public Cart addproductToCart(
            @RequestParam("cartId") Long cartId,
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity) {
        return cartService.addProductToCart(cartId, productId, quantity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductFromCartById(@PathVariable("id") Long cartId, @RequestParam("productId") Long productId) {
        cartService.deleteProductFromCart(cartId, productId);
    }
}
