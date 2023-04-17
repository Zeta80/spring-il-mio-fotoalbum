package com.learning.fotoalbum.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
            super(message);
        }

}
