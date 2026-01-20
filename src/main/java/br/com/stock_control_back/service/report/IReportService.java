package br.com.stock_control_back.service.report;

public interface IReportService {
    public byte[] generateBatchReport() throws Exception;
}
