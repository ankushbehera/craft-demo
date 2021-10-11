package com.craft.demo.mydata.repository;

import com.craft.demo.mydata.model.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author ANkush
 * @since 10-10-2021
 */
@Repository
public interface MyDataRepository extends ReactiveMongoRepository<Document, Integer> {
    @Query(value="{ 'timestamp': ?0, 'attributes.key': ?1, 'attributes.value': ?2}",
            fields = "{ id: 1 }")
    Flux<Document> getIds(Long timestamp, String key, String value, Pageable pageable);
}