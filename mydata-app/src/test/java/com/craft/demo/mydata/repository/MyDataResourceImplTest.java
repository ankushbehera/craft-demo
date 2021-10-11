package com.craft.demo.mydata.repository;

import com.craft.demo.mydata.model.Attribute;
import com.craft.demo.mydata.model.Document;
import com.craft.demo.mydata.resource.MyDataResourceImpl;
import com.craft.demo.mydata.service.MyDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**Error occurred
 * @author ANkush
 * @since 10-10-2021
 */
@ExtendWith(SpringExtension.class)
@WebFluxTest(MyDataResourceImpl.class)
@Import(MyDataServiceImpl.class)
class MyDataResourceImplTest    {
    @MockBean
    MyDataRepository repository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testCreateDocument() {
        Document document1 = Document.builder().id("id1").timestamp(1633870290L)
                .attributes(Arrays.asList(new Attribute("key1", "value1"), new Attribute("key2", "value2"))).build();
        Document document2 = Document.builder().id("id2").timestamp(1633870290L)
                .attributes(Arrays.asList(new Attribute("key2", "value2"), new Attribute("key3", "value3"))).build();
        Document document3 = Document.builder().id("id3").timestamp(1633870290L)
                .attributes(Arrays.asList(new Attribute("key4", "value4"), new Attribute("key5", "value5"))).build();
        Document document4 = Document.builder().id("id4").timestamp(1633870290L)
                .attributes(Arrays.asList(new Attribute("key2", "value2"), new Attribute("key1", "value1"))).build();
        List<Document> documents = new ArrayList<>();
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);
        documents.add(document4);
        Mockito.when(repository.insert(Flux.just(document1,document2,document3,document4))).thenReturn(Flux.just(document1,document2,document3,document4));

        webClient.put()
                .uri("/mydata")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(documents))
                .exchange()
                .expectStatus().isOk().expectBodyList(Document.class)
                .value(result ->
                        result.forEach( document ->
                                assertEquals(document.getTimestamp(), 1633870290L)));
    }

    @Test
    public void testIds() {
        Document document1 = Document.builder().id("id1").timestamp(1633870290L)
                .attributes(Arrays.asList(new Attribute("key1", "value1"), new Attribute("key2", "value2"))).build();
        Document document2 = Document.builder().id("id2").timestamp(1633870290L)
                .attributes(Arrays.asList(new Attribute("key2", "value2"), new Attribute("key1", "value1"))).build();
        Document document3 = Document.builder().id("id3").timestamp(1633870290L)
                .attributes(Arrays.asList(new Attribute("key1", "value1"), new Attribute("key5", "value5"))).build();
        Document document4 = Document.builder().id("id4").timestamp(1633870290L)
                .attributes(Arrays.asList(new Attribute("key2", "value2"), new Attribute("key1", "value1"))).build();
        Flux<Document> documentFlux = Flux.just(document1,document2,document3,document4);

        Mockito.when(repository.getIds(1633870290L,"key1","value1", PageRequest.of(0, 500))).thenReturn(documentFlux);

        webClient.get()
                .uri("/mydata/1633870290/key1/value1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk().expectBodyList(String.class)
                .value(result -> result.forEach(ids -> assertEquals(ids, "[\"id1\",\"id2\",\"id3\",\"id4\"]")));
    }
}