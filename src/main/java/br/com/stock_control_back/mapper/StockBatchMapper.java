package br.com.stock_control_back.mapper;

import br.com.stock_control_back.dto.stockbatch.StockBatchRequestDTO;
import br.com.stock_control_back.dto.stockbatch.StockBatchResponseDTO;
import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.entity.StockBatch;

import java.math.BigDecimal;

public class StockBatchMapper {

    private StockBatchMapper(){}

    public static StockBatch toEntity(
            StockBatchRequestDTO dto,
            Product product
    ) {
        StockBatch batch = new StockBatch();
        batch.setBatchNumber(dto.batchNumber());
        batch.setManufacturing(dto.manufacturing());
        batch.setValidity(dto.validity());
        batch.setQuantity(BigDecimal.valueOf(dto.quantity()));
        batch.setLocation(dto.location());
        batch.setProduct(product);
        return batch;
    }

    public static StockBatchResponseDTO toDTO(StockBatch batch) {
        return new StockBatchResponseDTO(
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
