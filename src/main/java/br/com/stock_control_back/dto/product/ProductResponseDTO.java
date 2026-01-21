package br.com.stock_control_back.dto.product;

import br.com.stock_control_back.entity.Product;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        String code,
        BigDecimal price,
        String productType
) {
    public ProductResponseDTO(Product product){
        this(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getPrice(),
                product.getProductType().toString()
        );
    }
}
