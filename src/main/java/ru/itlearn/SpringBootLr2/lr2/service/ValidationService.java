package ru.itlearn.SpringBootLr2.lr2.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.itlearn.SpringBootLr2.lr2.exception.ValidationFailedException;

@Service
public interface ValidationService {
void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
