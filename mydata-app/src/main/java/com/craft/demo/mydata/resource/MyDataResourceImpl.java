package com.craft.demo.mydata.resource;

import com.craft.demo.mydata.api.MyDataResource;
import com.craft.demo.mydata.model.Document;
import com.craft.demo.mydata.service.IMyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ANkush
 * @since 10-10-2021
 */
@RestController
public class MyDataResourceImpl implements MyDataResource {

    @Autowired
    IMyDataService dataService;

    @Override
    public Flux<Document> addDocuments(@Valid @RequestBody Flux<Document> documents) {
        return dataService.addDocuments(documents);
    }

    @Override
    public Mono<List<String>> getIds(Long timestamp, String key, String value) {
        return dataService.getIds(timestamp, key, value);
    }

}