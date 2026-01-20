package br.com.stock_control_back.service.stockbatch;

import br.com.stock_control_back.dto.stockbatch.StockBatchResponseDTO;
import br.com.stock_control_back.entity.StockBatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockBatchServiceTest {

    @Autowired
    IStockBatchService stockBatchService;


}
