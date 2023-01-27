package com.hefnawy.marty.auth.view;

import javafx.beans.property.SimpleStringProperty;

public class LoginViewModel {
    public LoginViewModel() {
    }

    public LoginViewModel(String userName, String password) {
        this.userNameProperty.set(userName);
        this.passwordProperty.set(password);
    }

    private final SimpleStringProperty userNameProperty = new SimpleStringProperty();
    private final SimpleStringProperty passwordProperty = new SimpleStringProperty();

    public String getUserName() {
        return userNameProperty.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userNameProperty;
    }

    public String getPassword() {
        return passwordProperty.get();
    }

    public SimpleStringProperty passwordProperty() {
        return passwordProperty;
    }

}
