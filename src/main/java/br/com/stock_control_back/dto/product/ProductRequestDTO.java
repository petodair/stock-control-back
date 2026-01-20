package br.com.stock_control_back.dto.product;

import br.com.stock_control_back.enums.ProductType;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String code,
        BigDecimal price,
        ProductType productType
) {}

