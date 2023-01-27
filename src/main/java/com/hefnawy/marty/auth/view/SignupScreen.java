package com.hefnawy.marty.auth.view;

import com.hefnawy.marty.auth.controller.SignupController;
import com.hefnawy.marty.auth.model.AuthErrorType;
import com.hefnawy.marty.auth.model.FieldType;
import com.hefnawy.marty.auth.model.User;
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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SignupScreen {
    private final TextField userNameField;
    private final Label userNameErrorLabel;
    private final PasswordField passwordField;
    private final Label passwordErrorLabel;
    private final PasswordField confirmPasswordField;
    private final Label confirmPasswordErrorLabel;
    private final Button signupButton;
    private final ProgressBar progressBar;
    private final SignupController signupController;
    public SignupScreen(SignupController controller) {
        signupController = controller;

        userNameField = new TextField();
        userNameErrorLabel = new Label();
        passwordField = new PasswordField();
        passwordErrorLabel = new Label();
        confirmPasswordField = new PasswordField();
        confirmPasswordErrorLabel = new Label();

        progressBar = new ProgressBar();
        //progressBar.prefWidthProperty().bind(stage.widthProperty().multiply(0.5));
        //progressBar.setManaged(false);
        //progressBar.setVisible(false);
        //progressBar.setOpacity(0.4);

        signupButton = new Button();

        onSignupBtnClicked();
        registerUserNameFieldListener();
        registerPasswordFieldListener();
        registerConfirmPasswordFieldListener();
    }

    public Scene getSignupScene(double width, double height) {
       /* Pane paneForProgressBar = new Pane();
        BorderPane borderPane = new BorderPane();

        paneForProgressBar.prefWidthProperty().bind(stage.widthProperty());
        paneForProgressBar.prefHeightProperty().bind(stage.heightProperty());

        borderPane.prefWidthProperty().bind(paneForProgressBar.prefWidthProperty());
        borderPane.prefHeightProperty().bind(paneForProgressBar.prefHeightProperty());*/

        HBox mainLayout = new HBox();

        mainLayout.prefWidthProperty().bind(signupController.mainController.getStage().widthProperty());
        mainLayout.prefHeightProperty().bind(signupController.mainController.getStage().heightProperty());

        VBox left = getLeft();
        HBox right = getRight();

        mainLayout.getChildren().add(left);
        mainLayout.getChildren().add(right);

       /* borderPane.getChildren().add(progressBar);
        BorderPane.setAlignment(progressBar, Pos.CENTER);
        paneForProgressBar.getChildren().addAll(mainLayout, borderPane);*/

        return new Scene(mainLayout, width, height);
    }

    private VBox getLeft() {
        VBox left = new VBox();
        left.setAlignment(Pos.CENTER);

        left.prefWidthProperty().bind(signupController.mainController.getStage().widthProperty().multiply(0.6));
        left.prefHeightProperty().bind(signupController.mainController.getStage().heightProperty());

        ImageView imageView = new ImageView();
        imageView.setImage(new Image("/super_market.png"));
        imageView.fitWidthProperty().bind(signupController.mainController.getStage().widthProperty().multiply(0.6));
        imageView.fitHeightProperty().bind(signupController.mainController.getStage().heightProperty());

        left.getChildren().add(imageView);

        return left;
    }

    private HBox getRight() {
        HBox right = new HBox();
        right.setAlignment(Pos.CENTER);
        right.setStyle("-fx-background-color: white");

        right.prefWidthProperty().bind(signupController.mainController.getStage().widthProperty().multiply(0.4));
        right.prefHeightProperty().bind(signupController.mainController.getStage().heightProperty());

        VBox rightVBox = new VBox();
        rightVBox.prefHeightProperty().bind(right.prefHeightProperty());
        rightVBox.prefWidthProperty().bind(right.prefWidthProperty());
        rightVBox.setAlignment(Pos.CENTER);
        HBox.setMargin(rightVBox, new Insets(30));

        VBox mainContent = new VBox();
        mainContent.prefWidthProperty().bind(rightVBox.widthProperty().multiply(0.7));
        //mainContent.prefHeightProperty().bind(rightVBox.heightProperty().multiply(0.7));

        // Sign up Label
        Label title = getLabel();
        // User Name
        VBox userNameContainer = getField(new FieldType.UserNameFieldType());
        // Password
        VBox passwordContainer = getField(new FieldType.PasswordFieldType());
        // Confirm Password
        VBox confirmPasswordContainer = getField(new FieldType.ConfirmPasswordFieldType());
        // Admin Check Box
        CheckBox adminCheckBox = getAdminCheckBox();
        // Sign up button
        HBox signupButton = getSignupButton(mainContent);
        //
        HBox goToLoginText = getGoToLoginText(mainContent);

        mainContent.getChildren().add(title);
        mainContent.getChildren().addAll(userNameContainer,
                passwordContainer,
                confirmPasswordContainer,
                adminCheckBox, signupButton, goToLoginText);

        rightVBox.getChildren().add(mainContent);

        right.getChildren().add(rightVBox);
        return right;
    }

    private static CheckBox getAdminCheckBox() {
        CheckBox adminCheckBox = new CheckBox("I'm an Admin");
        adminCheckBox.setStyle("-fx-font-size: 15px;");
        adminCheckBox.setPadding(new Insets(5, 5, 5, 5));
        VBox.setMargin(adminCheckBox, new Insets(5, 0, 10, 0));
        return adminCheckBox;
    }

    private  Label getLabel() {
        Label title = new Label("Sign up");
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
    private VBox getField(FieldType.ConfirmPasswordFieldType type) {
        VBox container = new VBox();
        Label label;
        label = new Label("Confirm Password");
        confirmPasswordField.setPromptText("Re-enter your password");

        VBox.setMargin(container, new Insets(10, 5, 10, 0));

        label.setStyle("-fx-font-family: sans-serif; -fx-font-size: 15px");
        VBox.setMargin(label, new Insets(0, 0, 5, 0));

        confirmPasswordField.setStyle("-fx-background-color: #ffebee; " +
                "-fx-prompt-text-fill: #707070; " +
                "-fx-background-radius: 10px;");
        confirmPasswordField.setPadding(new Insets(8));

        confirmPasswordErrorLabel.setTextFill(Color.color(1, 0 , 0));
        confirmPasswordErrorLabel.setStyle("-fx-font-size: 10px");
        confirmPasswordErrorLabel.setManaged(false);

        container.getChildren().addAll(label, confirmPasswordField, confirmPasswordErrorLabel);
        return container;
    }

    private HBox getSignupButton(VBox parent) {
        HBox container = new HBox();
        signupButton.setText("Sign up");
        signupButton.setStyle("-fx-background-color: #f44336; " +
                "-fx-background-radius: 10px;" +
                "-fx-text-fill: white");
        signupButton.setPadding(new Insets(8));
        VBox.setMargin(container, new Insets(12, 0, 12, 0));
        container.prefWidthProperty().bind(parent.prefWidthProperty().multiply(0.7));
        signupButton.prefWidthProperty().bind(container.prefWidthProperty());
        container.setAlignment(Pos.CENTER);

        container.getChildren().add(signupButton);
        return container;
    }

    private HBox getGoToLoginText(VBox parent){
        HBox container = new HBox();
        TextField text1 = new TextField("Already have an account?");
        text1.setEditable(false);
        text1.setBorder(null);
        text1.setPadding(new Insets(1, 0, 1, 1));
        text1.setStyle("-fx-text-fill: #707070;");

        Hyperlink text2 = new Hyperlink("Login");
        text2.setBorder(null);
        text2.setPadding(new Insets(1, 1, 1, 0));
        text2.setStyle("-fx-text-fill: #f44336;");
        text2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                signupController.mainController.openLoginScreen();
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

    private void onSignupBtnClicked(){
        signupButton.setOnAction(actionEvent -> {
            showProgressBar();
            SignupViewModel input = new SignupViewModel();
            input.setUserName(userNameField.getText().trim());
            input.setPassword(passwordField.getText().trim());
            input.setConfirmPassword(confirmPasswordField.getText().trim());
            signupController.onSignupBtnClicked(input);
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

    private void hideConfirmPasswordErrorLabel(){
        confirmPasswordErrorLabel.setVisible(false);
        confirmPasswordErrorLabel.setManaged(false);
    }
    private void showConfirmPasswordErrorLabel(){
        confirmPasswordErrorLabel.setVisible(true);
        confirmPasswordErrorLabel.setManaged(true);
    }

    public void onError(AuthErrorType.UserNameFieldError error){
        userNameField.setText("");
        userNameErrorLabel.setText(error.getMessage());
        showUserNameErrorLabel();
    }
    public void onError(AuthErrorType.UserNameAlreadyExistsError error){
        userNameField.setText("");
        userNameErrorLabel.setText(error.getMessage());
        showUserNameErrorLabel();
    }
    public void onError(AuthErrorType.PasswordFieldError error){
        passwordField.setText("");
        passwordErrorLabel.setText(error.getMessage());
        showPasswordErrorLabel();
    }
    public void onError(AuthErrorType.ConfirmPasswordFieldError error){
        confirmPasswordField.setText("");
        confirmPasswordErrorLabel.setText(error.getMessage());
        showConfirmPasswordErrorLabel();
    }

    private void registerUserNameFieldListener() {
            userNameField.textProperty().addListener((observableValue, oldValue, newValue) -> hideUserNameErrorLabel());
    }
    private void registerPasswordFieldListener() {
        passwordField.textProperty().addListener((observableValue, oldValue, newValue) -> hidePasswordErrorLabel());
    }
    private void registerConfirmPasswordFieldListener() {
        confirmPasswordField.textProperty().addListener((observableValue, oldValue, newValue) -> hideConfirmPasswordErrorLabel());
    }
}
