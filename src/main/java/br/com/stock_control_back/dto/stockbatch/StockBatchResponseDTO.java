package br.com.stock_control_back.dto.stockbatch;

import br.com.stock_control_back.enums.ProductType;
import br.com.stock_control_back.enums.StockLocation;

import java.time.LocalDate;

public record StockBatchResponseDTO(
        Long id,

        String batchNumber,
        LocalDate manufacturing,
        LocalDate validity,
        Double quantity,
        StockLocation location,

        // dados do produto
        Long productId,
        String productName,
        ProductType productType
) {}
