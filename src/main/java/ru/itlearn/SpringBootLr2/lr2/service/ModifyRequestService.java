package ru.itlearn.SpringBootLr2.lr2.service;

import org.springframework.stereotype.Service;
import ru.itlearn.SpringBootLr2.lr2.model.Request;
@Service
public interface ModifyRequestService {

    void modify(Request request);
}
