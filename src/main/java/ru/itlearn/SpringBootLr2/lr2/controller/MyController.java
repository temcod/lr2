package ru.itlearn.SpringBootLr2.lr2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itlearn.SpringBootLr2.lr2.exception.UnsupportedCodeException;
import ru.itlearn.SpringBootLr2.lr2.model.Request;
import ru.itlearn.SpringBootLr2.lr2.model.Response;
import ru.itlearn.SpringBootLr2.lr2.service.ValidationService;

@RestController
public class MyController {
    private final ValidationService validationService;
    @Autowired
    public MyController(ValidationService validationService) {
        this.validationService = validationService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {
        try {
            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException("Unsupported UID", "123");
            }
            if (!request.isValid()) {
                return new ResponseEntity<>(
                        Response.builder()
                                .code("error")
                                .errorCode("400")
                                .errorMessage("Invalid request")
                                .build(),
                        HttpStatus.BAD_REQUEST
                );
            }
            Response response = Response.builder()
                    .uid(request.getUid())
                    .operationUid(request.getOperationUid())
                    .systemTime(request.getSystemTime())
                    .code("success")
                    .errorCode("")
                    .errorMessage("")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException("Processing error", e);
        }
    }
    @ExceptionHandler(UnsupportedCodeException.class)
    public ResponseEntity<Response> handleUnsupportedCodeException(UnsupportedCodeException ex) {
        Response response = Response.builder()
                .code("error")
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
