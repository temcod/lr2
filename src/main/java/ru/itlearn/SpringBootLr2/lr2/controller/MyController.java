package ru.itlearn.SpringBootLr2.lr2.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itlearn.SpringBootLr2.lr2.exception.UnsupportedCodeException;
import ru.itlearn.SpringBootLr2.lr2.exception.ValidationFailedException;
import ru.itlearn.SpringBootLr2.lr2.model.*;
import ru.itlearn.SpringBootLr2.lr2.service.ModifyResponseService;
import ru.itlearn.SpringBootLr2.lr2.service.ValidationService;
import ru.itlearn.SpringBootLr2.lr2.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        log.info("Received request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessage.EMPTY)
                .build();
        log.info("Initialized response: {}", response);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("Validation error: {}", error.getDefaultMessage()));
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessage.VALIDATION);
            log.info("Validation failed: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            log.error("Validation failed exception: {}", e.getMessage(), e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessage.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unknown exception occurred: {}", e.getMessage(), e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessage.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        log.info("Modified response: {}", response);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }
}