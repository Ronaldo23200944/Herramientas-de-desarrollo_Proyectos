package com.marketplace.controller;

import com.marketplace.model.Order;
import com.marketplace.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 🔹 Crear orden desde el carrito
    @PostMapping("/create")
    public ResponseEntity<?> create() {
        try {
            return ResponseEntity.ok(orderService.createOrderFromCart());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Obtener orden por ID
@GetMapping("/{id}")
public ResponseEntity<?> getById(@PathVariable Long id) {

    // ❌ 400 - validación
    if (id == null || id <= 0) {
        return ResponseEntity.badRequest().body("ID inválido");
    }

    Order order = orderService.findById(id);

    // ❌ 404 - no existe
    if (order == null) {
        return ResponseEntity.status(404).body("Orden no encontrada");
    }

    // ✅ 200 - éxito
    return ResponseEntity.ok(order);
}

    // 🔹 Pagar orden
    @PostMapping("/{id}/pay")
    public ResponseEntity<?> pay(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.payOrder(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // 🔹 Cancelar orden
    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.cancelOrder(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}