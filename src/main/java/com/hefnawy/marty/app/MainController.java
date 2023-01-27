package com.hefnawy.marty.app;

import com.hefnawy.marty.auth.controller.LoginController;
import com.hefnawy.marty.auth.controller.SignupController;
import com.hefnawy.marty.home.view.HomeViewModel;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainController {
    Stage stage;
    private LoginController loginController;

    public MainController(Stage stage) {
        this.stage = stage;
        stage.getIcons().add(new Image("/logo.png"));
        stage.setTitle("Marty");
    }

    public void openLoginScreen(){
        if (loginController == null){
             loginController = new LoginController(this);
        }

        loginController.initialize();
    }

    public void openSignupScreen(){
        SignupController signupController = new SignupController(this);
        signupController.initialize();
    }

    public void openHomeScreen(HomeViewModel homeViewModel){

    }

    public Stage getStage() {
        return stage;
    }
}
