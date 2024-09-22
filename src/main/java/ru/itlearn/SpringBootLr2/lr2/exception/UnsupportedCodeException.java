package ru.itlearn.SpringBootLr2.lr2.exception;

public class UnsupportedCodeException extends RuntimeException {
    public UnsupportedCodeException(String message) {
        super(message);
    }
}