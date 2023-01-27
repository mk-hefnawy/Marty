package com.hefnawy.marty.auth.view;

import com.hefnawy.marty.auth.controller.LoginController;
import com.hefnawy.marty.auth.model.AuthErrorType;
import com.hefnawy.marty.auth.model.FieldType;
import com.hefnawy.marty.auth.model.User;
import com.hefnawy.marty.home.view.HomeViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class LoginScreen {
    public final TextField userNameField;
    private final Label userNameErrorLabel;
    public final PasswordField passwordField;
    private final Label passwordErrorLabel;
    private final Button loginButton;
    private final ProgressBar progressBar;
    private final LoginController loginController;


    public LoginScreen(LoginController controller) {
        loginController = controller;

        userNameField = new TextField();
        userNameErrorLabel = new Label();
        passwordField = new PasswordField();
        passwordErrorLabel = new Label();

        progressBar = new ProgressBar();
        //progressBar.prefWidthProperty().bind(stage.widthProperty().multiply(0.5));
        //progressBar.setManaged(false);
        //progressBar.setVisible(false);
        //progressBar.setOpacity(0.4);

        loginButton = new Button();

        restoreState();
        onLoginBtnClicked();
        registerUserNameFieldListener();
        registerPasswordFieldListener();
    }

    private void restoreState(){
        userNameField.setText(loginController.loginViewModel.getUserName());
        passwordField.setText(loginController.loginViewModel.getPassword());
    }

    public Scene getLoginScene(double width, double height) {
        /*Pane paneForProgressBar = new Pane();
        BorderPane borderPane = new BorderPane();

        paneForProgressBar.prefWidthProperty().bind(stage.widthProperty());
        paneForProgressBar.prefHeightProperty().bind(stage.heightProperty());

        borderPane.prefWidthProperty().bind(paneForProgressBar.prefWidthProperty());
        borderPane.prefHeightProperty().bind(paneForProgressBar.prefHeightProperty());*/

        HBox mainLayout = new HBox();

        mainLayout.prefWidthProperty().bind(loginController.mainController.getStage().widthProperty());
        mainLayout.prefHeightProperty().bind(loginController.mainController.getStage().heightProperty());

        VBox left = getLeft();
        HBox right = getRight();

        mainLayout.getChildren().add(left);
        mainLayout.getChildren().add(right);

        /*borderPane.getChildren().add(progressBar);
        BorderPane.setAlignment(progressBar, Pos.CENTER);
        paneForProgressBar.getChildren().addAll(mainLayout, borderPane);*/

        return new Scene(mainLayout, width, height);
    }

    private VBox getLeft() {
        VBox left = new VBox();
        left.setAlignment(Pos.CENTER);

        left.prefWidthProperty().bind(loginController.mainController.getStage().widthProperty().multiply(0.6));
        left.prefHeightProperty().bind(loginController.mainController.getStage().heightProperty());

        ImageView imageView = new ImageView();
        imageView.setImage(new Image("/super_market.png"));
        imageView.fitWidthProperty().bind(loginController.mainController.getStage().widthProperty().multiply(0.6));
        imageView.fitHeightProperty().bind(loginController.mainController.getStage().heightProperty());

        left.getChildren().add(imageView);

        return left;
    }

    private HBox getRight() {
        HBox right = new HBox();
        right.setAlignment(Pos.CENTER);
        right.setStyle("-fx-background-color: white");

        right.prefWidthProperty().bind(loginController.mainController.getStage().widthProperty().multiply(0.4));
        right.prefHeightProperty().bind(loginController.mainController.getStage().heightProperty());

        VBox rightVBox = new VBox();
        rightVBox.prefHeightProperty().bind(right.prefHeightProperty());
        rightVBox.prefWidthProperty().bind(right.prefWidthProperty());
        rightVBox.setAlignment(Pos.CENTER);
        HBox.setMargin(rightVBox, new Insets(30));

        VBox mainContent = new VBox();
        mainContent.prefWidthProperty().bind(rightVBox.widthProperty().multiply(0.7));
        //mainContent.prefHeightProperty().bind(rightVBox.heightProperty().multiply(0.7));

        // Login Label
        Label title = getLabel();
        // User Name
        VBox userNameContainer = getField(new FieldType.UserNameFieldType());
        // Password
        VBox passwordContainer = getField(new FieldType.PasswordFieldType());
        // Sign up button
        HBox signupButton = getLoginButton(mainContent);
        // Text Area
        HBox textArea = getGoToSignupText(mainContent);

        mainContent.getChildren().add(title);
        mainContent.getChildren().addAll(userNameContainer,
                passwordContainer, signupButton, textArea);

        rightVBox.getChildren().add(mainContent);

        right.getChildren().add(rightVBox);
        return right;
    }

    private  Label getLabel() {
        Label title = new Label("Login");
        title.setStyle("-fx-font-family: sans-serif; -fx-font-size: 25px; -fx-font-weight: 700");
        VBox.setMargin(title, new Insets(10, 10, 15, 0));
        return title;
    }

    private VBox getField(FieldType.UserNameFieldType type) {
        VBox container = new VBox();
        Label label;
        label = new Label("User name");
        userNameField.setPromptText("Enter your user name");

        VBox.setMargin(container, new Insets(10, 5, 10, 0));

        label.setStyle("-fx-font-family: sans-serif; -fx-font-size: 15px");
        VBox.setMargin(label, new Insets(0, 0, 5, 0));

        userNameField.setStyle("-fx-background-color: #ffebee; " +
                "-fx-prompt-text-fill: #707070; " +
                "-fx-background-radius: 10px;");
        userNameField.setPadding(new Insets(8));

        userNameErrorLabel.setTextFill(Color.color(1, 0 , 0));
        userNameErrorLabel.setStyle("-fx-font-size: 10px");
        userNameErrorLabel.setManaged(false);

        container.getChildren().addAll(label, userNameField, userNameErrorLabel);
        return container;
    }
    private VBox getField(FieldType.PasswordFieldType type) {
        VBox container = new VBox();
        Label label;
        label = new Label("Password");
        passwordField.setPromptText("Enter your password");

        VBox.setMargin(container, new Insets(10, 5, 10, 0));

        label.setStyle("-fx-font-family: sans-serif; -fx-font-size: 15px");
        VBox.setMargin(label, new Insets(0, 0, 5, 0));

        passwordField.setStyle("-fx-background-color: #ffebee; " +
                "-fx-prompt-text-fill: #707070; " +
                "-fx-background-radius: 10px;");
        passwordField.setPadding(new Insets(8));

        passwordErrorLabel.setTextFill(Color.color(1, 0 , 0));
        passwordErrorLabel.setStyle("-fx-font-size: 10px");
        passwordErrorLabel.setManaged(false);

        container.getChildren().addAll(label, passwordField, passwordErrorLabel);
        return container;
    }

    private HBox getLoginButton(VBox parent) {
        HBox container = new HBox();
        loginButton.setText("Login");
        loginButton.setStyle("-fx-background-color: #f44336; " +
                "-fx-background-radius: 10px;" +
                "-fx-text-fill: white");
        loginButton.setPadding(new Insets(8));
        VBox.setMargin(container, new Insets(12, 0, 12, 0));
        container.prefWidthProperty().bind(parent.prefWidthProperty().multiply(0.7));
        loginButton.prefWidthProperty().bind(container.prefWidthProperty());
        container.setAlignment(Pos.CENTER);

        container.getChildren().add(loginButton);
        return container;
    }

    private HBox getGoToSignupText(VBox parent){
        HBox container = new HBox();
        TextField text1 = new TextField("Don't have an account?");
        text1.setEditable(false);
        text1.setBorder(null);
        text1.setPadding(new Insets(1, 0, 1, 1));
        text1.setStyle("-fx-text-fill: #707070;");


        Hyperlink text2 = new Hyperlink("Create one");
        text2.setBorder(null);
        text2.setPadding(new Insets(1, 1, 1, 0));
        text2.setStyle("-fx-text-fill: #f44336;");
        text2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loginController.mainController.openSignupScreen();
            }
        });

        TextFlow textFlow = new TextFlow(text1, text2);
        container.setPadding(new Insets(8));
        VBox.setMargin(container, new Insets(12, 0, 12, 0));

        container.prefWidthProperty().bind(parent.prefWidthProperty().multiply(0.7));
        container.setAlignment(Pos.CENTER);

        container.getChildren().addAll(textFlow);
        return container;
    }

    private void onLoginBtnClicked(){
        loginButton.setOnAction(actionEvent -> {
            showProgressBar();
            loginController.onLoginBtnClicked();
        });
    }

    private void showProgressBar(){
        progressBar.setVisible(true);
        progressBar.setManaged(true);
    }
    private void hideProgressBar(){

    }
    private void hideUserNameErrorLabel(){
        userNameErrorLabel.setVisible(false);
        userNameErrorLabel.setManaged(false);
    }
    private void showUserNameErrorLabel(){
        userNameErrorLabel.setVisible(true);
        userNameErrorLabel.setManaged(true);
    }

    private void hidePasswordErrorLabel(){
        passwordErrorLabel.setVisible(false);
        passwordErrorLabel.setManaged(false);
    }
    private void showPasswordErrorLabel(){
        passwordErrorLabel.setVisible(true);
        passwordErrorLabel.setManaged(true);
    }

    public void onError(AuthErrorType.UserNameFieldError error){
        userNameField.setText("");
        userNameErrorLabel.setText(error.getMessage());
        showUserNameErrorLabel();
    }
    public void onError(AuthErrorType.NoSuchUserError error){
        userNameErrorLabel.setText(error.getMessage());
        showUserNameErrorLabel();
    }
    public void onError(AuthErrorType.PasswordFieldError error){
        passwordField.setText("");
        passwordErrorLabel.setText(error.getMessage());
        showPasswordErrorLabel();
    }
    public void onError(AuthErrorType.IncorrectPasswordError error) {
        passwordField.setText("");
        passwordErrorLabel.setText(error.getMessage());
        showPasswordErrorLabel();
    }

    private void registerUserNameFieldListener() {
        userNameField.textProperty().addListener((observableValue, oldValue, newValue) -> hideUserNameErrorLabel());
    }
    private void registerPasswordFieldListener() {
        passwordField.textProperty().addListener((observableValue, oldValue, newValue) -> hidePasswordErrorLabel());
    }
}
