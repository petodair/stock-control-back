package br.com.stock_control_back.entity;

import br.com.stock_control_back.enums.StockLocation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockBatch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private LocalDate manufacturing;

    @Column(nullable = false)
    private LocalDate validity;

    @Column(precision = 18, scale = 2)
    private BigDecimal quantity;

    /** Numero do lote */
    private String batchNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private StockLocation location;

}
