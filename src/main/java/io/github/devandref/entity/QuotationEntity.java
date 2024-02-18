package io.github.devandref.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "quotation")
public class QuotationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @Column(name = "currency_price")
    private BigDecimal currencyPrice;
    @Column(name = "pct_change")
    private String pctChange;
    private String pair;

}
