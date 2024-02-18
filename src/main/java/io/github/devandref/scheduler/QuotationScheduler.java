package io.github.devandref.scheduler;

import io.github.devandref.message.KafkaEvents;
import io.github.devandref.service.QuotationService;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class QuotationScheduler {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    @Inject
    private QuotationService quotationService;

    @Scheduled(every = "35s", identity = "task-job")
    @Transactional
    void scheduler() {
        LOG.info("-- Executando Scheduler --");
        quotationService.getCurrencyPrice();
    }
}
