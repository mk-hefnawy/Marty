package com.hefnawy.marty.auth.controller;

import com.hefnawy.marty.app.MainController;
import com.hefnawy.marty.auth.model.AuthErrorType;
import com.hefnawy.marty.auth.model.InvalidUserInputException;
import com.hefnawy.marty.auth.model.LoginUseCase;
import com.hefnawy.marty.auth.model.User;
import com.hefnawy.marty.auth.model.dao.DaoException;
import com.hefnawy.marty.app.Dimensions;
import com.hefnawy.marty.auth.view.LoginScreen;
import com.hefnawy.marty.auth.view.LoginViewModel;
import com.hefnawy.marty.home.view.HomeViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class LoginController{

    private SimpleObjectProperty<User> loginResultProperty = new SimpleObjectProperty<>();
    private LoginScreen view;
    private final LoginUseCase loginUseCase;
    public LoginViewModel loginViewModel;
    public MainController mainController;

    public LoginController(MainController mainController) {
        loginUseCase = new LoginUseCase();
        loginViewModel = new LoginViewModel();
        loginResultProperty.bind(loginUseCase.loginResultProperty());
        registerUserPropertyListener();

        this.mainController = mainController;
    }

    public void initialize() {
        view = new LoginScreen(this);
        loginViewModel.userNameProperty().bind(view.userNameField.textProperty());
        loginViewModel.passwordProperty().bind(view.passwordField.textProperty());

        Scene loginScene = view.getLoginScene(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        mainController.getStage().getIcons().add(new Image("/logo.png"));
        mainController.getStage().setScene(loginScene);
        mainController.getStage().show();
    }

    public void onLoginBtnClicked() {
        User user = new User();
        user.setUserName(loginViewModel.getUserName());
        user.setPassword(loginViewModel.getPassword());

        new Thread(() -> {
            try {
                loginUseCase.login(user);
            } catch (InvalidUserInputException.UserNameException e) {
                Platform.runLater(() -> {view.onError(new AuthErrorType.UserNameFieldError(e.getMessage()));});

            }catch (InvalidUserInputException.PasswordException e) {
                Platform.runLater(() -> {view.onError(new AuthErrorType.PasswordFieldError(e.getMessage()));});
            }
            catch (DaoException.NoSuchUserException e) {
                Platform.runLater(() -> {
                    view.onError(new AuthErrorType.NoSuchUserError(e.getMessage()));
                });
            }
            catch (DaoException.IncorrectPasswordException e) {
                Platform.runLater(() -> {
                    view.onError(new AuthErrorType.IncorrectPasswordError(e.getMessage()));
                });
            }
        }).start();
    }

    private void registerUserPropertyListener(){
        loginResultProperty.addListener((observableValue, user, newUser) -> {
            // start new page with user info
            HomeViewModel homeViewModel = new HomeViewModel(user.getUserName(), user.getUserRole());
            // That newUser has to be HomeViewModel
            Platform.runLater(() -> openHomeScreen(homeViewModel));

        });
    }


    public void openHomeScreen(HomeViewModel homeViewModel) {
            mainController.openHomeScreen(homeViewModel);
    }
}
