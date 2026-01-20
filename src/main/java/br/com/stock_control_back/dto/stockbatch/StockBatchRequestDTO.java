package br.com.stock_control_back.dto.stockbatch;

import br.com.stock_control_back.enums.StockLocation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
) {}
