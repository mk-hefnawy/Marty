package com.hefnawy.marty.auth.model;

public class InvalidUserInputException extends Exception{
    public InvalidUserInputException(String message) {
        super(message);
    }

    // UserName
    public static class UserNameException extends InvalidUserInputException{
        public UserNameException(String message) {
            super(message);
        }
    }
    // Password
    public static class PasswordException extends InvalidUserInputException{
        public PasswordException(String message) {
            super(message);
        }
    }
}
