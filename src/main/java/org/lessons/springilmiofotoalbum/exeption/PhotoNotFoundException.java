package org.lessons.springilmiofotoalbum.exeption;

public class PhotoNotFoundException extends RuntimeException{
        public PhotoNotFoundException(String message) {
            super(message);
        }
    }

