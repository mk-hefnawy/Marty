package com.hefnawy.marty.auth.view;

import com.hefnawy.marty.auth.model.UserRole;

public class SignupViewModel {

    public SignupViewModel() {
    }

    public SignupViewModel(String userName, String password, String confirmPassword,UserRole userRole) {
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userRole = userRole;
    }
    private String userName;
    private String password;
    private String confirmPassword;
    private UserRole userRole;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
