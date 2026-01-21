package br.com.stock_control_back.controller;

import br.com.stock_control_back.dto.ApiResponse;
import br.com.stock_control_back.dto.product.ProductRequestDTO;
import br.com.stock_control_back.dto.product.ProductResponseDTO;
import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.enums.ProductType;
import br.com.stock_control_back.service.product.IProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> save(@RequestBody ProductRequestDTO product) {

        ApiResponse<Product> apiResponse = this.productService.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(apiResponse.data().getId())
                .toUri();

        return ResponseEntity.created(location).body(apiResponse);
    }

    @GetMapping
    public ApiResponse<List<ProductResponseDTO>> findAll(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) ProductType productType) {
        return this.productService.findAll(pageable, name, code, productType);
    }

    @GetMapping("/{id}")
    public ApiResponse<Product> findById(@PathVariable Long id){
        return this.productService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequestDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(productService.deleteById(id), HttpStatus.NO_CONTENT);
    }

}
