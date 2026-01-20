package br.com.stock_control_back.service.product;

import br.com.stock_control_back.dto.ApiResponse;
import br.com.stock_control_back.dto.product.ProductRequestDTO;
import br.com.stock_control_back.dto.product.ProductResponseDTO;
import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.enums.ProductType;
import br.com.stock_control_back.mapper.ProductMapper;
import br.com.stock_control_back.repository.ProductRepository;
import br.com.stock_control_back.specification.ProductSpecification;
import br.com.stock_control_back.validator.ProductValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    public ProductService(
            ProductRepository productRepository,
            ProductValidator productValidator
    ) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    @Override
    @Transactional
    public ApiResponse<Product> save(ProductRequestDTO productDTO) {

        Product product = ProductMapper.toEntity(productDTO);
        productValidator.validate(product);

        return new ApiResponse<>(ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.CREATED,
                this.productRepository.save(product),
                "Produto salvo com sucesso!");
    }

    @Override
    public ApiResponse<Product> findByCode(String code){
        Product product = this.productRepository.findByCode(code).get();

        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.OK,
                product,
                "Pagina de produtos retornada com sucesso!"
        );
    }

    @Override
    public ApiResponse<List<ProductResponseDTO>> findAll(Pageable pageable, String name,
                                                         String code, ProductType productType) {

        Page<Product> products = productRepository.findAll(
                ProductSpecification.filter(name, code, productType),
                pageable);

        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.OK,
                products.map(ProductMapper::toDTO).getContent(),
                "Pagina de produtos retornada com sucesso!");
    }

    @Override
    public ApiResponse<Product> findById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.OK,
                product,
                "Produto retornado com sucesso!");
    }

    @Override
    @Transactional
    public ApiResponse<ProductResponseDTO> update(
            Long id,
            ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        product.setName(dto.name());
        product.setCode(dto.code());
        product.setPrice(dto.price());
        product.setProductType(dto.productType());

        //productRepository.save(product);

        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.OK,
                ProductMapper.toDTO(product),
                "Produto atualizado com sucesso");
    }

    @Transactional
    public ApiResponse<Void> deleteById(Long id){
        boolean productExists = productRepository.existsById(id);

        if(productExists){
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Produto não encontrado para ser deletado!");
        }

        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.NO_CONTENT,
                null,
                "Produto deletado com sucesso!"
        );
    }

}
