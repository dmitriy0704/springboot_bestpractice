package my.home.todo.validation_rest;

import my.home.todo.validation_rest.AppException;

public class NotFoundException extends AppException {

    public NotFoundException(String message) {
        super(message);
    }

}