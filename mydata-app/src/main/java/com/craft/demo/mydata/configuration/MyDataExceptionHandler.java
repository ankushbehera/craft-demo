package com.craft.demo.mydata.configuration;

import com.craft.demo.mydata.exception.DataNotFoundException;
import com.craft.demo.mydata.model.ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.ValidationException;

/**
 * @author ANkush
 * @since 10-10-2021
 */
@Configuration
@Order(-2)
public class MyDataExceptionHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyDataExceptionHandler.class);

    public MyDataExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        LOGGER.error("Error occurred", throwable);
        DataBuffer dataBuffer;
        if (throwable instanceof DuplicateKeyException) {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.CONFLICT);
            serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            dataBuffer = getErrorMessage(serverWebExchange, "Id already exists");
        } else if (throwable instanceof DataNotFoundException) {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            dataBuffer = getErrorMessage(serverWebExchange, throwable.getMessage());
        } else if (throwable instanceof WebExchangeBindException) {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            dataBuffer = getErrorMessage(serverWebExchange, throwable.getMessage());
        } else if (throwable instanceof ValidationException) {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            dataBuffer = getErrorMessage(serverWebExchange, throwable.getMessage());
        } else {
            serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            dataBuffer = getErrorMessage(serverWebExchange, "Server Error");
        }
        return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    DataBuffer getErrorMessage(ServerWebExchange serverWebExchange, String message) {
        DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
        DataBuffer dataBuffer;
        try {
            dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(new ResponseMessage(message)));
        } catch (JsonProcessingException e) {
            dataBuffer = bufferFactory.wrap(message.getBytes());
        }
        return dataBuffer;
    }
}