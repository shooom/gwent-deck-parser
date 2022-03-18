package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.exceptions.CardNotFoundException;
import com.parser.gwentdeckparser.exceptions.GwentParserException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GwentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GwentParserException.class})
    public ResponseEntity<Object> unifiedHandler(RuntimeException ex, WebRequest req) {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.UNPROCESSABLE_ENTITY, req);
    }

    @ExceptionHandler({CardNotFoundException.class})
    public ResponseEntity<Object> notFoundException(RuntimeException ex, WebRequest req) {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return handleExceptionInternal(ex, ex.getMessage(), headers,  HttpStatus.NOT_FOUND, req);
    }
}
