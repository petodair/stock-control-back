package br.com.stock_control_back.service.stockbatch;

import br.com.stock_control_back.dto.ApiResponse;
import br.com.stock_control_back.dto.stockbatch.StockBatchRequestDTO;
import br.com.stock_control_back.dto.stockbatch.StockBatchResponseDTO;
import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.entity.StockBatch;
import br.com.stock_control_back.enums.StockLocation;
import br.com.stock_control_back.repository.ProductRepository;
import br.com.stock_control_back.repository.StockBatchRepository;
import br.com.stock_control_back.specification.StockBatchSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockBatchService implements IStockBatchService{

    private final StockBatchRepository stockBatchRepository;
    private final ProductRepository productRepository;

    public StockBatchService(StockBatchRepository stockBatchRepository,
                             ProductRepository productRepository) {
        this.stockBatchRepository = stockBatchRepository;
        this.productRepository = productRepository;
    }

    public ApiResponse<StockBatch> save(StockBatchRequestDTO dto) {
        Optional<Product> product = productRepository.findById(dto.productId());
        if(product.isEmpty()){
            throw new EntityNotFoundException("produto não encontrado para a operação");
        }

        StockBatch stockBatch = dto.toEntity(product.get());

        this.stockBatchRepository.save(stockBatch);

        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.CREATED,
                stockBatch,
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
                page.map(StockBatchResponseDTO::new).getContent(),
                "Lista de produtos retornados com sucesso");
    }

    @Transactional
    @Override
    public ApiResponse<StockBatchResponseDTO> update(Long id,StockBatchRequestDTO dto) {
        if (stockBatchRepository.existsById(id)) {
            throw new EntityNotFoundException("Lote não encontrado para alteração!");
        }
        Optional<Product> product = productRepository.findById(dto.productId());
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado para alteração!");
        }

        StockBatch stockBatch = dto.toEntity(product.get());
        stockBatch.setId(id);

        stockBatchRepository.save(stockBatch);

        return new ApiResponse<>(
                ApiResponse.ResponseStatusType.SUCCESS,
                HttpStatus.NO_CONTENT,
                new StockBatchResponseDTO(stockBatch),
                "Item em estoque atualizado com sucesso!");
    }

}
