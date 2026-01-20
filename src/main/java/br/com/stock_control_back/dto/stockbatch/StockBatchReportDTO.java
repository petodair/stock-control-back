package br.com.stock_control_back.dto.stockbatch;

import java.math.BigDecimal;

public record StockBatchReportDTO(
        String productName,
        String productCode,
        BigDecimal price,

        String batchNumber,
        String manufacturing,
        String validity,
        BigDecimal quantity,
        String location
) {
}
