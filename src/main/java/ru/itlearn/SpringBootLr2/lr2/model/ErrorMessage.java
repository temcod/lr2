package ru.itlearn.SpringBootLr2.lr2.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessage {

    EMPTY(""),
    VALIDATION("ошибка валидации"),
    UNSUPPORTED("произошла непредвиденная ошибка"),
    UNKNOWN("не поддерживаемая ошибка");

    private final String description;

    ErrorMessage(String description) {
        this.description = description;
    }
    @JsonValue
    public String getName(){
        return description;
    }
}
