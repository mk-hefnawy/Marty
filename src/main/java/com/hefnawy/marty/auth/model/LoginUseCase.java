package com.hefnawy.marty.auth.model;

import com.hefnawy.marty.auth.model.dao.AuthDAO;
import com.hefnawy.marty.auth.model.dao.AuthDaoImpl;
import com.hefnawy.marty.auth.model.dao.DaoException;
import javafx.beans.property.SimpleObjectProperty;

public class LoginUseCase {
    private SimpleObjectProperty<User> loginResultProperty = new SimpleObjectProperty();
    private AuthDAO dao =  AuthDaoImpl.getInstance();;

    public SimpleObjectProperty<User> loginResultProperty() {
        return loginResultProperty;
    }

    {
        loginResultProperty.bind(dao.loginResultProperty());
    }
    public void login(User user) throws InvalidUserInputException.UserNameException,
            InvalidUserInputException.PasswordException, DaoException.NoSuchUserException,
            DaoException.IncorrectPasswordException {

        // Validation Logic
        if (user.getUserName().trim().equals("") || user.getUserName() == null)
            throw new InvalidUserInputException.UserNameException("Please, enter your user name");
        if (user.getUserName().trim().length() < 6)
            throw new InvalidUserInputException.UserNameException("User name must have 6 characters or more");

        if (user.getPassword().trim().equals("") || user.getPassword() == null)
            throw new InvalidUserInputException.PasswordException("Please, enter your password");
        if (user.getPassword().trim().length() < 8)
            throw new InvalidUserInputException.PasswordException("Password must have 8 characters or more");

        dao.login(user);
    }
}
