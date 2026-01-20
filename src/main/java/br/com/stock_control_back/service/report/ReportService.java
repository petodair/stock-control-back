package br.com.stock_control_back.service.report;

import br.com.stock_control_back.dto.stockbatch.StockBatchReportDTO;
import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.entity.StockBatch;
import br.com.stock_control_back.repository.StockBatchRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService implements IReportService{

    private final StockBatchRepository stockBatchRepository;

    public ReportService(StockBatchRepository stockBatchRepository) {
        this.stockBatchRepository = stockBatchRepository;
    }

    public byte[] generateBatchReport() throws Exception {
        List<StockBatch> batches = stockBatchRepository.findAll();

        List<StockBatchReportDTO> reportData = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (StockBatch batch : batches) {
            Product product = batch.getProduct();

            reportData.add(new StockBatchReportDTO(
                    product.getName(),
                    product.getCode(),
                    product.getPrice(),
                    batch.getBatchNumber(),
                    batch.getManufacturing().format(formatter),
                    batch.getValidity().format(formatter),
                    batch.getQuantity(),
                    batch.getLocation().toString(),
                    batch.getQuantity().multiply(product.getPrice())
            ));
        }

        File file = ResourceUtils.getFile("classpath:reports/stock_batch.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
