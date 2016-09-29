package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;

public class MainFXApplication extends Application {
    // TODO: Put this in a ResourceBundle instead
    private static final String TITLE = "Canteen: A Water Tracker";

    private User user;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        primaryStage.setTitle(TITLE);
        showRegister();
        primaryStage.show();
    }

    private void showView(String name) {
        URL path = getClass().getResource("/view/" + name + "View.fxml");

        // If getResource() does not find the resource given, it returns
        // null
        if (path == null) {
            bail("Cannot locate view " + name + ".");
            return;
        }

        FXMLLoader loader = new FXMLLoader(path);
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            bail("IOException thrown when loading view '" + name + "'.");
            return;
        }

        // TODO: Use dependency injection or something instead of this
        // interface-cast hack
        ((IMainAppReceiver)loader.getController()).setMainApp(this);

        stage.setScene(new Scene(root));
    }

    public void showAlert(String message) {
        Alert alert = new WarningAlert(message);
        alert.showAndWait();
    }

    private void bail(String message) {
        showAlert(message + "\nBailing out!");
        close();
    }

    /**
     * Ends the application.
     * See: http://stackoverflow.com/a/12154135/321301
     */
    public void close() {
        stage.close();
    }

    /**
     * Stores the account used to authenticate after login has succeeded
     * @param user Account used to authenticate
     */
    public void loginComplete(User user) {
        this.user = user;
        showView("Success");
    }

    public void showRegister() {
        showView("Welcome");
    }

    public void showLogin() {
        showView("Login");
    }

    public void logout() {
        this.user = null;
        showRegister();
    }

    public void finishRegistration(User user) {
        this.user = user;
        showView("Profile");
    }

    public void registrationComplete() {
        showView("Success");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
