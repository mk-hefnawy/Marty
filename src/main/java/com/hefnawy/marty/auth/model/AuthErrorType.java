package com.hefnawy.marty.auth.model;

public interface AuthErrorType {
     String getMessage();

    class UserNameAlreadyExistsError implements AuthErrorType {
        private String message;
        public UserNameAlreadyExistsError(String message){this.message = message;}

        @Override
        public String getMessage() {
            return message;
        }
    }
  class UserNameFieldError implements AuthErrorType {
      private String message;

      @Override
      public String getMessage() {
          return message;
      }

      public UserNameFieldError(String message){this.message = message;}
  }
  class PasswordFieldError implements AuthErrorType {
      private String message;

      @Override
      public String getMessage() {
          return message;
      }
      public PasswordFieldError(String message){this.message = message;}
  }
  class ConfirmPasswordFieldError implements AuthErrorType {
      private String message;

      @Override
      public String getMessage() {
          return message;
      }

      public ConfirmPasswordFieldError(String message) {
          this.message = message;
      }
  }

    class NoSuchUserError implements AuthErrorType {
        private String message;

        @Override
        public String getMessage() {
            return message;
        }

        public NoSuchUserError(String message) {
            this.message = message;
        }
    }

    public class IncorrectPasswordError implements AuthErrorType {
        private String message;

        @Override
        public String getMessage() {
            return message;
        }

        public IncorrectPasswordError(String message) {
          this.message = message;
        }
    }
}
