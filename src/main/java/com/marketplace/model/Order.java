package com.marketplace.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Order {

    private Long id;
    private List<CartItem> items = new ArrayList<>();
    private String status;
    private double total;
    private LocalDateTime fecha;

    public Order() {}

    public Order(Long id, List<CartItem> items, String status, double total, LocalDateTime fecha) {
        this.id = id;
        this.items = items;
        this.status = status;
        this.total = total;
        this.fecha = fecha;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}