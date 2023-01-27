package com.hefnawy.marty.auth.model.preferences;

public interface PreferencesService {

    boolean isUserLoggedIn(String userName);
    void loginUser(String userName);
    void logOutUser(String userName);
}
