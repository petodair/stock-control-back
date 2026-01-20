package br.com.stock_control_back.service.product;

import br.com.stock_control_back.dto.ApiResponse;
import br.com.stock_control_back.dto.product.ProductRequestDTO;
import br.com.stock_control_back.dto.product.ProductResponseDTO;
import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.enums.ProductType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    ApiResponse<Product> save(ProductRequestDTO product);

    ApiResponse<List<ProductResponseDTO>> findAll(Pageable pageable, String name,
                                                  String code, ProductType productType);

    ApiResponse<Product> findByCode(String code);

    ApiResponse<Product> findById(Long id);

    ApiResponse<ProductResponseDTO> update(Long id,ProductRequestDTO product);

    ApiResponse<Void> deleteById(Long id);

}
