package ru.itlearn.SpringBootLr2.lr2.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itlearn.SpringBootLr2.lr2.model.Request;
import ru.itlearn.SpringBootLr2.lr2.model.Systems;

@Service
public class ModifySystemNameRequestService implements ModifyRequestService{
    private static final String NEW_SOURCE_VALUE = "newSourceValue"; // Новое значение для поля source

    @Override
    public void modify(Request request) {
        if (request != null) {
            request.setSource(NEW_SOURCE_VALUE);
        }
    }

}
