package com.hefnawy.marty.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        MainController mainController = new MainController(stage);
        mainController.openLoginScreen();

    }

    public static void main(String[] args) {
        launch();
    }

}