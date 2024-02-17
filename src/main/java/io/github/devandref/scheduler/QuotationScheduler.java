package io.github.devandref.scheduler;

import io.github.devandref.service.QuotationService;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class QuotationScheduler {

    @Inject
    private QuotationService quotationService;

    @Scheduled(every = "35s", identity = "task-job")
    @Transactional
    void scheduler() {
        quotationService.getCurrencyPrice();
    }
}
