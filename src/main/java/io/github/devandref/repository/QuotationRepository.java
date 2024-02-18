package io.github.devandref.repository;

import io.github.devandref.entity.QuotationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuotationRepository implements PanacheRepository<QuotationEntity> {
}
