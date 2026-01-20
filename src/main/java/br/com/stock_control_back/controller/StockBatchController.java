package br.com.stock_control_back.controller;

import br.com.stock_control_back.dto.ApiResponse;
import br.com.stock_control_back.dto.stockbatch.StockBatchRequestDTO;
import br.com.stock_control_back.dto.stockbatch.StockBatchResponseDTO;
import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.entity.StockBatch;
import br.com.stock_control_back.enums.StockLocation;
import br.com.stock_control_back.mapper.StockBatchMapper;
import br.com.stock_control_back.service.product.ProductService;
import br.com.stock_control_back.service.stockbatch.IStockBatchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock-batch")
public class StockBatchController {

    private final IStockBatchService stockBatchService;
    private final ProductService productService;

    public StockBatchController(IStockBatchService stockBatchService, ProductService productService) {
        this.stockBatchService = stockBatchService;
        this.productService = productService;
    }

    @PostMapping
    public ApiResponse<StockBatch> save(
            @Valid @RequestBody StockBatchRequestDTO dto) {
        Product product = productService.findById(dto.productId()).data();

        StockBatch batch = StockBatchMapper.toEntity(dto, product);
        return stockBatchService.save(batch);
    }

    @GetMapping
    public ApiResponse<List<StockBatchResponseDTO>> findAll(
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) StockLocation location,
            @RequestParam(required = false) Boolean expired) {
        return this.stockBatchService.findAll(pageable, location, expired);
    }

    public ApiResponse<StockBatch> update(
            @Valid @RequestBody StockBatchRequestDTO dto
    ){
        Product product = productService.findById(dto.productId()).data();

        StockBatch batch = StockBatchMapper.toEntity(dto, product);
        return stockBatchService.update(batch);
    }
}
