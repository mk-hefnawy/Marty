package com.hefnawy.marty.auth.model.preferences;

import java.util.prefs.Preferences;

public class PreferencesServiceImpl implements PreferencesService{
    private final String PREFERENCES_NAME = "prefs";
    private final String USER_NAME = "UserName";
    private Preferences preferences;
    @Override
    public boolean isUserLoggedIn(String userName) {
        preferences = Preferences.userRoot().node(PREFERENCES_NAME);
        String existingUserName = preferences.get(USER_NAME, "");
        preferences = null;
        return !existingUserName.equals("");
    }

    @Override
    public void loginUser(String userName) {
        preferences = Preferences.userRoot().node(PREFERENCES_NAME);
        preferences.put(USER_NAME, userName);
        preferences = null;
    }

    @Override
    public void logOutUser(String userName) {
        preferences = Preferences.userRoot().node(PREFERENCES_NAME);
        preferences.remove(USER_NAME);
        preferences = null;
    }
}
