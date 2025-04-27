package com.iit.tutorials.jfxgtds;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class LoginController {

    @FXML
    public Label loginLabal;
    @FXML
    public PasswordField passInput;
    @FXML
    public TextField useNameText;
    @FXML
    public Label messageLabel;


    public void onLoginClick() throws IOException {
        String username = useNameText.getText();
        String password = passInput.getText();

        if (username.equals("admin") && password.equals("admin")) {
            openMainView(username);
        } else if (username.equals("user") && password.equals("user")) {
            openMainView(username);
        } else {
            messageLabel.setText("Invalid username or password");
            useNameText.clear();
            passInput.clear();
        }
    }

    private void openMainView(String username) throws IOException {
        Stage mainStage = (Stage) loginLabal.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setLoggedInUser(username);
        mainStage.setScene(new Scene(root, 1000, 800));
        mainStage.show();

    }
}
