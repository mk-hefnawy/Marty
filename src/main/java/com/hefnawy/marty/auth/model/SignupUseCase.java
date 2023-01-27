package com.hefnawy.marty.auth.model;

import com.hefnawy.marty.auth.model.dao.AuthDAO;
import com.hefnawy.marty.auth.model.dao.AuthDaoImpl;
import com.hefnawy.marty.auth.model.dao.DaoException;
import javafx.beans.property.SimpleObjectProperty;

import static com.hefnawy.marty.auth.model.InvalidUserInputException.*;
public class SignupUseCase {
    private SimpleObjectProperty userProperty = new SimpleObjectProperty();
    AuthDAO dao = AuthDaoImpl.getInstance();

    {
        userProperty.bind(dao.userProperty());
    }

    public void signup(User user) throws UserNameException,
                                         PasswordException,
            DaoException.UserAlreadyExistsException {
        // Validation Logic
        if (user.getUserName().trim().equals("") || user.getUserName() == null) throw new UserNameException("Please, enter your user name");
        if (user.getUserName().trim().length() < 6) throw new UserNameException("User name must have 6 characters or more");

        if (user.getPassword().trim().equals("") || user.getPassword() == null) throw new PasswordException("Please, enter your password");
        if (user.getPassword().trim().length() < 8) throw new PasswordException("Password must have 8 characters or more");

        dao.signup(user);
    }

    public SimpleObjectProperty<User> userProperty() {
        return userProperty;
    }
}
