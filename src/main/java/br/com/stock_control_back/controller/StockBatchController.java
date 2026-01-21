package br.com.stock_control_back.controller;

import br.com.stock_control_back.dto.ApiResponse;
import br.com.stock_control_back.dto.stockbatch.StockBatchRequestDTO;
import br.com.stock_control_back.dto.stockbatch.StockBatchResponseDTO;
import br.com.stock_control_back.enums.StockLocation;
import br.com.stock_control_back.service.stockbatch.IStockBatchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock-batchs")
public class StockBatchController {

    private final IStockBatchService stockBatchService;

    public StockBatchController(IStockBatchService stockBatchService) {
        this.stockBatchService = stockBatchService;
    }

    @PostMapping
    public ApiResponse<StockBatchResponseDTO> save(
            @Valid @RequestBody StockBatchRequestDTO dto) {
        return stockBatchService.save(dto);
    }

    @GetMapping
    public ApiResponse<List<StockBatchResponseDTO>> findAll(
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) StockLocation location,
            @RequestParam(required = false) Boolean expired) {
        return this.stockBatchService.findAll(pageable, location, expired);
    }

    @PutMapping("/{id}")
    public ApiResponse<StockBatchResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody StockBatchRequestDTO dto
    ){
        return stockBatchService.update(id,dto);
    }
}
