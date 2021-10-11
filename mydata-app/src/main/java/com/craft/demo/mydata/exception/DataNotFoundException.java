package com.craft.demo.mydata.exception;

/**
 * @author ANkush
 * @since 10-10-2021
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
