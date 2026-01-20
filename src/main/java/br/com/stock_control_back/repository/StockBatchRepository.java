package br.com.stock_control_back.repository;

import br.com.stock_control_back.entity.StockBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockBatchRepository extends JpaRepository<StockBatch, Long>,
        JpaSpecificationExecutor<StockBatch> {
}
