package com.marketplace.service;

import com.marketplace.model.CartItem;
import com.marketplace.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private CartService cartService;

    public Order createOrderFromCart() {

        List<CartItem> cart = cartService.getCart();

        if (cart.isEmpty()) {
            throw new RuntimeException("Carrito vacío");
        }

        Order order = new Order();
        order.setId(nextId++);
        order.setItems(new ArrayList<>(cart));
        order.setStatus("CREATED");

        // 🔥 NUEVO: calcular total desde los items
        double total = cart.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();

        order.setTotal(total);

        // 🔥 NUEVO: fecha de creación
        order.setFecha(LocalDateTime.now());

        orders.add(order);

        cartService.clearCart();

        return order;
    }

    public Order findById(Long id) {
        return orders.stream()
                .filter(o -> o.getId() != null && o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Order payOrder(Long id) {

        Order order = findById(id);

        if (order == null) {
            throw new RuntimeException("Orden no encontrada");
        }

        order.setStatus("PAID");
        return order;
    }

    public Order cancelOrder(Long id) {

        Order order = findById(id);

        if (order == null) {
            throw new RuntimeException("Orden no encontrada");
        }

        order.setStatus("CANCELLED");
        return order;
    }
}