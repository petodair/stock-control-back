package br.com.stock_control_back.validator;

import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.repository.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validate(Product product) {
        if(productCodeExists(product.getCode())) {
            throw new ValidationException("Já existe um produto com esse código registrado.");
        }
    }

    public boolean productCodeExists(String code){
        return productRepository.existsByCode(code);
    }
}
