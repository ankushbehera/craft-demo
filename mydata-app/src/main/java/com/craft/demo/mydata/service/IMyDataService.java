package com.craft.demo.mydata.service;

import com.craft.demo.mydata.model.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ANkush
 * @since 10-10-2021
 */
public interface IMyDataService {

    Flux<Document> addDocuments(@Valid Flux<Document> documents);

    Mono<List<String>> getIds(Long timestamp, String key, String value);
}