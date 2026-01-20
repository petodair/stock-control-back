package br.com.stock_control_back.mapper;

import br.com.stock_control_back.dto.product.ProductRequestDTO;
import br.com.stock_control_back.dto.product.ProductResponseDTO;
import br.com.stock_control_back.entity.Product;

public class ProductMapper {

    private ProductMapper(){}

    public static ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getPrice(),
                product.getProductType().toString()
        );
    }

    public static Product toEntity(ProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setName(productRequestDTO.name());
        product.setCode(productRequestDTO.code());
        product.setPrice(productRequestDTO.price());
        product.setProductType(productRequestDTO.productType());

        return product;
    }

}
