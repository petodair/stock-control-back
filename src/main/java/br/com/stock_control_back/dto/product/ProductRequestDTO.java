package br.com.stock_control_back.dto.product;

import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.enums.ProductType;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String code,
        BigDecimal price,
        ProductType productType
) {
    public Product toEntity(){
        Product product = new Product();
        product.setName(name);
        product.setCode(code);
        product.setPrice(price);
        product.setProductType(productType);
        return product;
    }
}

