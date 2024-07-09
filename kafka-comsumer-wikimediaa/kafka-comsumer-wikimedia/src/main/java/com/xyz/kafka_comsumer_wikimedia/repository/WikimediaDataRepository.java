package com.xyz.kafka_comsumer_wikimedia.repository;

import com.xyz.kafka_comsumer_wikimedia.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData,Long> {
}
