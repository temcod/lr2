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
import ru.itlearn.SpringBootLr2.lr2.service.ModifyRequestService;
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
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
    ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) throws ValidationFailedException {
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

        try {
            validationService.isValid((BindingResult) request);
        } catch (ValidationFailedException e) {
            log.error("Validation failed: {}", e.getMessage(), e);
            // Исключение будет обработано глобальным обработчиком
            throw e;}
        return null;
    }
}


