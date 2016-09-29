package controller;

import javafx.IMainAppReceiver;
import javafx.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import model.User;
import model.DataSource;
import exception.NoSuchUserException;

/**
 * Created by jitaekim on 9/18/16.
 */
public class LoginController implements IMainAppReceiver {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    private MainFXApplication mainApplication;

    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }
    private void handleCloseMenu() {
        mainApplication.close();
    }

    public void handleLoginPressed(ActionEvent actionEvent) {
        try {
            User user = DataSource.getInstance().authenticate(
                username.getText(), password.getText());

            mainApplication.loginComplete(user);
        } catch (NoSuchUserException e) {
            mainApplication.showAlert(e.getMessage());
        }
    }

    public void handleBackButtonPressed(ActionEvent actionEvent) {
        mainApplication.showRegister();
    }
}
