package com.craft.demo.mydata.api;

import com.craft.demo.mydata.model.Document;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-10-10T13:13:44.218448900+05:30[Asia/Calcutta]")
@Validated
@Api(value = "mydata", description = "the mydata API")
public interface MyDataResource {

    /**
     * PUT /mydata : Add new documents
     *
     * @param document addDocuments (required)
     * @return Documents Added (status code 200)
     */
    @ApiOperation(value = "Add new documents", nickname = "addDocuments", notes = "", response = Document.class, responseContainer = "List", tags={  })

    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Documents Added", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Document.class)))) })

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/mydata",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    Flux<Document> addDocuments(@ApiParam(value = "addDocuments" ,required=true )  @Valid @RequestBody Flux<Document> documents);


    /**
     * GET /mydata/{timestamp}/{key}/{value} : Get list of ids matching the key, value, timestamp
     *
     * @param timestamp timestamp (required)
     * @param key key (required)
     * @param value value (required)
     * @return successful operation (status code 200)
     */
    @ApiOperation(value = "Get list of ids matching the key, value, timestamp", nickname = "getIds", notes = "", response = String.class, responseContainer = "List", tags={  })
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))) })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/mydata/{timestamp}/{key}/{value}",
            produces = { "application/json" }
    )
    Mono<List<String>> getIds(@ApiParam(value = "timestamp",required=true) @PathVariable("timestamp") Long timestamp,@Size(max=256) @ApiParam(value = "key",required=true) @PathVariable("key") String key,@Size(max=256) @ApiParam(value = "value",required=true) @PathVariable("value") String value);

}
