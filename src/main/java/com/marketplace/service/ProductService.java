package com.marketplace.service;

import com.marketplace.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Producto 1", new BigDecimal("100"), 10));
        products.add(new Product(2L, "Producto 2", new BigDecimal("200"), 5));
    }

    public List<Product> getAll() {
        return products;
    }

    public Product getById(Long id) {
        return products.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product applyDiscount(Long id, double discount) {
        Product product = getById(id);

        if (product == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        if (discount < 0 || discount > 1) {
            throw new RuntimeException("Descuento inválido");
        }

        BigDecimal porcentaje = BigDecimal.valueOf(discount);
        BigDecimal descuento = product.getPrecio().multiply(porcentaje);
        BigDecimal nuevoPrecio = product.getPrecio().subtract(descuento);

        product.setPrecio(nuevoPrecio);

        return product;
    }
    public void deleteById(Long id) {

    Product p = getById(id);

    if (p == null) {
        throw new RuntimeException("Producto no encontrado");
    }

    getAll().remove(p);
}
}