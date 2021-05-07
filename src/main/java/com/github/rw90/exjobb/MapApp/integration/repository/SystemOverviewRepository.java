package com.github.rw90.exjobb.MapApp.integration.repository;

import com.github.rw90.exjobb.MapApp.model.SystemOverviewWrapper;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface SystemOverviewRepository extends ReactiveMongoRepository<SystemOverviewWrapper, UUID> {
}