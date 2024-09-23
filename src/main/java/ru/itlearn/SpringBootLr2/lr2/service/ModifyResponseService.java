package ru.itlearn.SpringBootLr2.lr2.service;

import org.springframework.stereotype.Service;
import ru.itlearn.SpringBootLr2.lr2.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);

}
