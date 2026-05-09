package com.marketplace.model;

import java.time.LocalDateTime;

public class CartItem {

    private Product product;
    private int cantidad;
    private double subtotal;
    private LocalDateTime fechaAgregado;

    public CartItem() {}

    public CartItem(Product product, int cantidad, double subtotal, LocalDateTime fechaAgregado) {
        this.product = product;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.fechaAgregado = fechaAgregado;
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public LocalDateTime getFechaAgregado() { return fechaAgregado; }
    public void setFechaAgregado(LocalDateTime fechaAgregado) { this.fechaAgregado = fechaAgregado; }
}