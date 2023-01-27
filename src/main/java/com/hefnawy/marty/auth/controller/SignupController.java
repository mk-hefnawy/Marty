package com.hefnawy.marty.auth.controller;

import com.hefnawy.marty.app.MainController;
import com.hefnawy.marty.auth.model.AuthErrorType;
import com.hefnawy.marty.auth.model.SignupUseCase;
import com.hefnawy.marty.auth.model.User;
import com.hefnawy.marty.auth.model.dao.DaoException;
import com.hefnawy.marty.app.Dimensions;
import com.hefnawy.marty.auth.view.SignupScreen;
import com.hefnawy.marty.auth.view.SignupViewModel;
import com.hefnawy.marty.home.view.HomeViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;

import static com.hefnawy.marty.auth.model.InvalidUserInputException.*;

public class SignupController {
    private SimpleObjectProperty<User> userProperty = new SimpleObjectProperty<>();
    private SignupScreen view;
    private SignupUseCase useCase;
    public MainController mainController;

    public SignupController(MainController mainController) {
        this.mainController = mainController;
        useCase = new SignupUseCase();
        userProperty.bind(useCase.userProperty());
        registerUserPropertyListener();
    }

    public void initialize(){
        view = new SignupScreen(this);

        Scene signupScene = view.getSignupScene(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        mainController.getStage().setScene(signupScene);
        mainController.getStage().show();
    }

    public void onSignupBtnClicked(SignupViewModel input) {
        if(!input.getPassword().equals(input.getConfirmPassword())){
            view.onError(new AuthErrorType.ConfirmPasswordFieldError("Passwords don't match"));
        }

        User user = new User();
        user.setUserName(input.getUserName());
        user.setPassword(input.getPassword());
        user.setUserRole(input.getUserRole());

        new Thread(() -> {
            try {
                useCase.signup(user);
            } catch (UserNameException e) {
                Platform.runLater(() -> {view.onError(new AuthErrorType.UserNameFieldError(e.getMessage()));});

            }catch (PasswordException e) {
                Platform.runLater(() -> {view.onError(new AuthErrorType.PasswordFieldError(e.getMessage()));});
            }
            catch (DaoException.UserAlreadyExistsException e){
                Platform.runLater(() -> {view.onError(new AuthErrorType.UserNameAlreadyExistsError(e.getMessage()));});
            }
        }).start();
    }

    private void registerUserPropertyListener(){
        userProperty.addListener((observableValue, user, newUser) -> {
            // start new page with user info
            HomeViewModel homeViewModel = new HomeViewModel(user.getUserName(), user.getUserRole());

            Platform.runLater(() -> {openHomeScreen(homeViewModel);});
        });
    }

    private void openHomeScreen(HomeViewModel homeViewModel){
        mainController.openHomeScreen(homeViewModel);
    }
}
