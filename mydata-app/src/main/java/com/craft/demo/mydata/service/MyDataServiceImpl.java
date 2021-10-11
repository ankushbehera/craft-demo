package com.craft.demo.mydata.service;

import com.craft.demo.mydata.exception.DataNotFoundException;
import com.craft.demo.mydata.model.Document;
import com.craft.demo.mydata.repository.MyDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ANkush
 * @since 10-10-2021
 */
@Service
public class MyDataServiceImpl implements IMyDataService {

    @Value("${query.id.limit}")
    private int limit;

    @Autowired
    MyDataRepository myDataRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyDataServiceImpl.class);

    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Document> addDocuments(@Valid @RequestBody Flux<Document> documents) {
        LOGGER.info("Adding document");
        return myDataRepository.insert(documents);
    }

    @Override
    public Mono<List<String>> getIds(Long timestamp, String key, String value) {
        LOGGER.info("Fetching id for timestamp - {}, key - {}, value - {} ", timestamp, key, value);
        Flux<Document> all = myDataRepository.getIds(timestamp, key, value, PageRequest.of(0, limit)).switchIfEmpty(Mono.error(new DataNotFoundException("Data Not Found for the given input")));
        return all.mapNotNull(Document::getId).collect(Collectors.toList());
    }
}