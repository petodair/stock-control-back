package br.com.stock_control_back.service.stockbatch;

import br.com.stock_control_back.dto.ApiResponse;
import br.com.stock_control_back.dto.stockbatch.StockBatchResponseDTO;
import br.com.stock_control_back.entity.StockBatch;
import br.com.stock_control_back.enums.StockLocation;
import br.com.stock_control_back.mapper.StockBatchMapper;
import br.com.stock_control_back.repository.StockBatchRepository;
import br.com.stock_control_back.specification.StockBatchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockBatchService implements IStockBatchService{

    private final StockBatchRepository stockBatchRepository;

    public StockBatchService(StockBatchRepository stockBatchRepository) {
        this.stockBatchRepository = stockBatchRepository;
    }

    public ApiResponse<StockBatch> save(StockBatch stockBatch) {
        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.CREATED,
                this.stockBatchRepository.save(stockBatch),
                "Item em estoque salvo com sucesso!");
    }

    public ApiResponse<List<StockBatchResponseDTO>> findAll(Pageable pageable, StockLocation location,
                                                            Boolean expired) {
        Page<StockBatch> page = this.stockBatchRepository.findAll(
                StockBatchSpecification.filter(location, expired),
                pageable);

        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.OK,
                page.map(StockBatchMapper::toDTO).getContent(),
                "Lista de produtos retornados com sucesso");
    }

    @Override
    public ApiResponse<StockBatch> update(StockBatch stockBatch) {
        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.CREATED,
                this.stockBatchRepository.save(stockBatch),
                "Item em estoque atualizado com sucesso!");
    }

}
