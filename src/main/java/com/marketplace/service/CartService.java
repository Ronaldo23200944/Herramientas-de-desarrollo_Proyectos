package com.marketplace.service;

import com.marketplace.model.CartItem;
import com.marketplace.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private List<CartItem> cart = new ArrayList<>();

    @Autowired
    private ProductService productService;

    public List<CartItem> getCart() {
        return cart;
    }

    public CartItem addProduct(Long productId, int cantidad) {

        if (cantidad <= 0) {
            throw new RuntimeException("Cantidad inválida");
        }

        Product product = productService.getById(productId);

        if (product == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        if (product.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        // 🔥 NUEVO: calcular subtotal
        double subtotal = product.getPrecio().doubleValue() * cantidad;

        // 🔥 NUEVO: crear con todos los atributos
        CartItem item = new CartItem(
                product,
                cantidad,
                subtotal,
                LocalDateTime.now()
        );

        cart.add(item);

        return item;
    }

    public double total() {
        return cart.stream()
                .mapToDouble(CartItem::getSubtotal) // 🔥 ahora usa subtotal
                .sum();
    }

    public void clearCart() {
        cart.clear();
    }
}