package io.github.devandref.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class QuotationDTO {

    private LocalDate localDate;
    private BigDecimal currencyPrice;

}
