package io.github.devandref.service;

import io.github.devandref.client.CurrencyPriceClient;
import io.github.devandref.dto.CurrencyPriceDTO;
import io.github.devandref.dto.QuotationDTO;
import io.github.devandref.entity.QuotationEntity;
import io.github.devandref.message.KafkaEvents;
import io.github.devandref.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class QuotationService {

    @Inject
    @RestClient
    private CurrencyPriceClient currencyPriceClient;

    @Inject
    private QuotationRepository quotationRepository;

    @Inject
    private KafkaEvents kafkaEvents;

    public void getCurrencyPrice() {
        CurrencyPriceDTO currencyPriceInfo = currencyPriceClient.getPriceByPair("USD-BRL");
        if (updateCurrentInfoPrice(currencyPriceInfo)) {
            kafkaEvents.sendNewKafkaEvent(QuotationDTO
                    .builder()
                    .currencyPrice(new BigDecimal(currencyPriceInfo.getUsdbrl().getBid()))
                    .localDate(LocalDate.now())
                    .build());
        }
    }

    private boolean updateCurrentInfoPrice(CurrencyPriceDTO currencyPriceInfo) {
        BigDecimal currentPrice = new BigDecimal(currencyPriceInfo.getUsdbrl().getBid());
        AtomicBoolean updatePrice = new AtomicBoolean(false);

        List<QuotationEntity> quotationList = quotationRepository.findAll().list();

        if(quotationList.isEmpty()) {
            saveQuotation(currencyPriceInfo);
            updatePrice.set(true);
        } else {
            QuotationEntity lastDollarPrice = quotationList.get(quotationList.size() - 1);

            if(currentPrice.floatValue() > lastDollarPrice.getCurrencyPrice().floatValue()) {
                updatePrice.set(true);
                saveQuotation(currencyPriceInfo);
            }
        }
        return updatePrice.get();
    }

    private void saveQuotation(CurrencyPriceDTO currencyInfo) {
        QuotationEntity quotationEntity = new QuotationEntity();

        quotationEntity.setDate(LocalDate.now());
        quotationEntity.setCurrencyPrice(new BigDecimal(currencyInfo.getUsdbrl().getBid()));
        quotationEntity.setPctChange(currencyInfo.getUsdbrl().getPctChange());
        quotationEntity.setPair("USD-BRL");

        quotationRepository.persist(quotationEntity);
    }

}
