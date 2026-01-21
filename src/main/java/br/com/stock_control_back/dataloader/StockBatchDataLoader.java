package br.com.stock_control_back.dataloader;

import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.entity.StockBatch;
import br.com.stock_control_back.enums.StockLocation;
import br.com.stock_control_back.repository.ProductRepository;
import br.com.stock_control_back.repository.StockBatchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Order(value = 2)
public class StockBatchDataLoader implements CommandLineRunner {

    private final StockBatchRepository stockBatchRepository;
    private final ProductRepository productRepository;

    public StockBatchDataLoader(StockBatchRepository stockBatchRepository,
                                ProductRepository productRepository) {
        this.stockBatchRepository = stockBatchRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<StockBatch> batches = Arrays.asList(
                createStockBatch(1L,"2026-01-11", "2026-02-23",
                        "30.21", "L0001"),
                createStockBatch(2L,"2025-12-11", "2026-01-24",
                        "20.45", "L0002"),
                createStockBatch(1L,"2026-01-01", "2026-02-27",
                        "60.22", "L0003"),
                createStockBatch(4L,"2026-01-05", "2026-02-23",
                        "20.22", "L0004"),
                createStockBatch(6L,"2026-01-10", "2026-02-27",
                        "40.00", "L0005")

        );

        this.stockBatchRepository.saveAll(batches);
    }

    private StockBatch createStockBatch(Long productId, String manufacturing,
                                        String validity, String quantity, String batchNumber
    ) {
        StockBatch stockBatch = new StockBatch();
        Product product = new Product();
        product.setId(productId);
        stockBatch.setProduct(product);
        stockBatch.setManufacturing(LocalDate.parse(manufacturing));
        stockBatch.setValidity(LocalDate.parse(validity));
        stockBatch.setQuantity(new BigDecimal(quantity));
        stockBatch.setBatchNumber(batchNumber);
        stockBatch.setLocation(StockLocation.ESTOQUE);

        return stockBatch;
    }
}
