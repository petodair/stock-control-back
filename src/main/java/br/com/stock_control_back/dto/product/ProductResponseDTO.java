package br.com.stock_control_back.dto.product;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        String code,
        BigDecimal price,
        String productType
) {}
