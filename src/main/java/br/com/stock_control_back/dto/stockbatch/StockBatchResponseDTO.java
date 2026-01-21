package br.com.stock_control_back.dto.stockbatch;

import br.com.stock_control_back.entity.StockBatch;
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
) {
    public StockBatchResponseDTO(StockBatch batch){
        this(
                batch.getId(),
                batch.getBatchNumber(),
                batch.getManufacturing(),
                batch.getValidity(),
                batch.getQuantity().doubleValue(),
                batch.getLocation(),

                batch.getProduct().getId(),
                batch.getProduct().getName(),
                batch.getProduct().getProductType()
        );
    }
}
