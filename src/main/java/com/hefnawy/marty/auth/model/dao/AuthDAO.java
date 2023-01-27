package com.hefnawy.marty.auth.model.dao;

import com.hefnawy.marty.auth.model.User;
import javafx.beans.property.SimpleObjectProperty;

public interface AuthDAO {

    void signup(User user) throws DaoException.UserAlreadyExistsException;

    void login(User user)  throws DaoException.NoSuchUserException, DaoException.IncorrectPasswordException;
    SimpleObjectProperty<User> userProperty();
    SimpleObjectProperty<User> loginResultProperty();
}
