package br.com.stock_control_back.dto.stockbatch;

import java.math.BigDecimal;

public class StockBatchReportDTO {
    private String productName;
    private String productCode;
    private BigDecimal price;

    private String batchNumber;
    private String manufacturing;
    private String validity;
    private BigDecimal quantity;
    private String location;

    public StockBatchReportDTO(String productName, String productCode, BigDecimal price,
                               String batchNumber, String manufacturing, String validity,
                               BigDecimal quantity, String location) {
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
        this.batchNumber = batchNumber;
        this.manufacturing = manufacturing;
        this.validity = validity;
        this.quantity = quantity;
        this.location = location;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getManufacturing() {
        return manufacturing;
    }

    public void setManufacturing(String manufacturing) {
        this.manufacturing = manufacturing;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
