package com.hefnawy.marty.auth.model.dao;

import com.hefnawy.marty.auth.model.User;
import com.hefnawy.marty.db.DatabaseConnectionUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import javafx.beans.property.SimpleObjectProperty;

public class AuthDaoImpl implements AuthDAO {
    private SimpleObjectProperty<User> userProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<User> loginResultProperty = new SimpleObjectProperty<>();
    private AuthDaoImpl() {
    }

    private static AuthDaoImpl instance;

    public static AuthDaoImpl getInstance() {
        if (instance == null) return new AuthDaoImpl();
        return instance;
    }

    EntityManager entityManager;

    @Override
    public void signup(User user) throws DaoException.UserAlreadyExistsException {
        entityManager  = DatabaseConnectionUtil.getEntityManager();
        Long userCount = entityManager.createQuery("SELECT COUNT (u) FROM User u WHERE u.userName=:userName", Long.class)
                .setParameter("userName", user.getUserName())
                .getSingleResult();
        if (userCount == 1L) {
            throw new DaoException.UserAlreadyExistsException("User name already taken");
        }

        EntityTransaction transaction  = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        User res =  entityManager.createQuery("SELECT u FROM User u WHERE u.userName=:userName", User.class)
                .setParameter("userName", user.getUserName())
                .getSingleResult();

        entityManager.close();
        userProperty.set(res);
    }

    @Override
    public void login(User user) throws DaoException.NoSuchUserException, DaoException.IncorrectPasswordException {
        entityManager  = DatabaseConnectionUtil.getEntityManager();
        Long userCount = entityManager.createQuery("SELECT COUNT (u) FROM User u WHERE u.userName=:userName", Long.class)
                .setParameter("userName", user.getUserName())
                .getSingleResult();
        if (userCount == 0L) {
            throw new DaoException.NoSuchUserException("User name does not exist");
        }

        User res =  entityManager.createQuery("SELECT u FROM User u WHERE u.userName=:userName", User.class)
                .setParameter("userName", user.getUserName())
                .getSingleResult();

        if (user.getPassword().equals(res.getPassword())){
            loginResultProperty.set(res);
        }else {
            throw new DaoException.IncorrectPasswordException("The password is incorrect");
        }
        entityManager.close();
    }

    public SimpleObjectProperty<User> userProperty() {
        return userProperty;
    }
    public SimpleObjectProperty<User> loginResultProperty() {
        return loginResultProperty;
    }
}
