package br.com.stock_control_back.service.stockbatch;

import br.com.stock_control_back.dto.ApiResponse;
import br.com.stock_control_back.dto.stockbatch.StockBatchRequestDTO;
import br.com.stock_control_back.dto.stockbatch.StockBatchResponseDTO;
import br.com.stock_control_back.entity.StockBatch;
import br.com.stock_control_back.enums.StockLocation;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStockBatchService {

    public ApiResponse<StockBatchResponseDTO> save(StockBatchRequestDTO dto);

    public ApiResponse<List<StockBatchResponseDTO>> findAll(Pageable pageable, StockLocation location,
                                                            Boolean expired);

    public ApiResponse<StockBatchResponseDTO> update(Long id,StockBatchRequestDTO dto);

}
