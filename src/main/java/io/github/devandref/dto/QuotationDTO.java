package io.github.devandref.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class QuotationDTO {

    private LocalDate localDate;
    private BigDecimal currencyPrice;

}
