package com.marketplace.controller;

import com.marketplace.model.CartItem;
import com.marketplace.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 🔹 Agregar producto al carrito
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam Long productId,
                                @RequestParam int quantity) {
        try {
            CartItem item = cartService.addProduct(productId, quantity);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Obtener todos los items del carrito
@GetMapping
public ResponseEntity<?> getCart() {

    List<CartItem> cart = cartService.getCart();

    // ❌ 400 opcional (validación lógica)
    if (cart == null) {
        return ResponseEntity.badRequest().body("Error al obtener carrito");
    }

    // ⚠️ 200 siempre (carrito puede estar vacío)
    return ResponseEntity.ok(cart);
}

    // 🔹 Obtener total del carrito
    @GetMapping("/total")
    public ResponseEntity<?> total() {
        try {
            return ResponseEntity.ok(cartService.total());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Vaciar carrito
    @DeleteMapping("/clear")
    public ResponseEntity<?> clear() {
        cartService.clearCart();
        return ResponseEntity.ok("Carrito vaciado");
    }
}