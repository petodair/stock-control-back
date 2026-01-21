package br.com.stock_control_back.dto.stockbatch;

import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.entity.StockBatch;
import br.com.stock_control_back.enums.StockLocation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StockBatchRequestDTO(

        @NotNull
        Long productId,

        @NotBlank
        String batchNumber,

        @NotNull
        LocalDate manufacturing,

        @NotNull
        LocalDate validity,

        @NotNull
        Double quantity,

        @NotNull
        StockLocation location
) {
        public StockBatch toEntity(Product product){
                StockBatch batch = new StockBatch();
                batch.setBatchNumber(batchNumber);
                batch.setManufacturing(manufacturing);
                batch.setValidity(validity);
                batch.setQuantity(BigDecimal.valueOf(quantity));
                batch.setLocation(location);
                batch.setProduct(product);
                return batch;
        }
}
