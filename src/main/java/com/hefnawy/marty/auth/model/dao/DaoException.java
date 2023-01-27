package com.hefnawy.marty.auth.model.dao;

public interface DaoException {
     class NoSuchUserException extends Exception{
        public NoSuchUserException(String message) {
            super(message);
        }
    }

     class UserAlreadyExistsException extends Exception{
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

     class IncorrectPasswordException extends Exception{
        public IncorrectPasswordException(String message) {
            super(message);
        }
    }
}
