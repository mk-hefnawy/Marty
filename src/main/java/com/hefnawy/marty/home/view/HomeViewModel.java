package com.hefnawy.marty.home.view;

import com.hefnawy.marty.auth.model.UserRole;

public class HomeViewModel {
    private String userName;
    private UserRole userRole;

    public HomeViewModel(String userName, UserRole userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
