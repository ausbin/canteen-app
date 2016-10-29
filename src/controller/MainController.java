package controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.MainAppReceiver;
import javafx.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Handles events from the main screen
 */
public class MainController implements MainAppReceiver {
    private MainFXApplication mainApp;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private StackPane mainPane;

    private HamburgerBackArrowBasicTransition transition;

    @Override
    public void setMainApp(MainFXApplication mainApp) {
        this.mainApp = mainApp;

        drawer.setSidePane(loadView("DrawerContent"));
        showMap();
    }

    /**
     * Creates a hamburger that pulls out a drawer when clicked. It also
     * transitions to an arrow when clicked and back when clicked again.
     * This is used as the primary method to navigate the app.
     */
    @FXML
    private void initialize() {

        transitionHelper();
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)-> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            drawer.setOnDrawerClosed(e2 -> drawer.toBack());
            drawer.setOnDrawerOpening(e2 -> drawer.toFront());

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });

    }

    /**
     * A private helper method to allow constant use of the hamburger
     * transition across multiple methods in the class without having
     * to create new instance of the HamburgerBackArrowBasicTransition
     */
    private void transitionHelper() {
        transition = new
                HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
    }

    /**
     * When the logout button is pressed, informs MainFXApp, which will unset
     * the current user and display the registration screen.
     */
    @FXML
    private void handleLogoutPressed(ActionEvent event) {
        mainApp.logout();
    }

    /**
     * When user pressed edit profile button, displays edit profile screen.
     * @param event event raised
     */
    @FXML
    private void handleEditProfile(ActionEvent event) {
        mainApp.editProfile();
    }

    /**
     * Slides the drawer shut.
     */
    private void closeDrawer() {
        transition.setRate(-1);
        transition.play();
        drawer.close();
    }

    /**
     * Shows the map screen.
     */
    public void showMap() {
        //closeDrawer();
        showView("Map");
        closeDrawer();
    }

    /**
     * Shows the report list screen.
     */
    public void showReportList() {
        closeDrawer();
        showView("ReportList");
    }

    /**
     * Shows the create report screen.
     */
    public void showCreateReport() {
        showView("CreateReport");
        closeDrawer();
    }

    /**
     * Shows the purity report list screen.
     */
    public void showPurityReportList() {
        showView("PurityReportList");
        closeDrawer();
    }

    /**
     * Shows the purity report editor.
     */
    public void showCreatePurityReport() {
        showView("CreatePurityReport");
        closeDrawer();
    }

    /**
     * Shows the edit profile screen.
     */
    public void showEditProfile() {
        showView("Profile");
        closeDrawer();
    }

    /**
     * Shows the HistoricalReportView
     */
    public void showHistoricalReport() {
        showView("HistoricalReport");
        closeDrawer();
    }

    /**
     * Loads the root node of another view, and pass the controller a reference
     * to this instance.
     */
    private Parent loadView(String name) {
        return mainApp.loadView(name, c -> {
            MainControllerReceiver controller = (MainControllerReceiver) c;
            controller.setMainController(this);
        });
    }

    /**
     * Loads view and places it in the center of the screen.
     */
    private void showView(String name) {
        mainPane.getChildren().setAll(loadView(name));
    }
}
