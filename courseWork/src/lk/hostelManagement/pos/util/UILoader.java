package lk.hostelManagement.pos.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
public class UILoader {

    public static void loadUiDashBoard(AnchorPane anchorPane, String location) throws IOException {

        /**----------------------------------FULL CODE------------------------------------------
         URL resource = getClass().getResource("../view/" + location + ".fxml");
         Parent load = FXMLLoader.load(resource);
         anchorPane.getChildren().clear();
         anchorPane.getChildren().add(load);
         */

        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(UILoader.class.getResource("/lk/hostelManagement/pos/view/" + location + ".fxml"))));
    }

    public static void LoginOnAction(AnchorPane anchorPane, String location) throws IOException, SQLException {
        /**----------------------------------FULL CODE------------------------------------------
         URL resource = UILoader.class.getResource("/view/" + location + ".fxml");
         Parent load = FXMLLoader.load(resource);
         Stage window = (Stage) anchorPane.getScene().getWindow();
         window.setScene(new Scene(load));
         */

        Stage window = (Stage) anchorPane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(UILoader.class.getResource("/lk/hostelManagement/pos/view/" + location + ".fxml")))));
    }

    public static void NavigateToHome(AnchorPane anchorPane, String location) throws IOException, SQLException {
        /**----------------------------------FULL CODE------------------------------------------
         URL resource = this.getClass().getResource("/com/superMarket/pos/view/CashierDashBoardForm.fxml");
         Parent root = FXMLLoader.load(resource);
         Scene scene = new Scene(root);
         Stage primaryStage = (Stage) (this.CustomerFormContext.getScene().getWindow());
         primaryStage.setScene(scene);
         primaryStage.centerOnScreen();
         Platform.runLater(() -> primaryStage.sizeToScene());
         */

        Stage primaryStage = (Stage) (anchorPane.getScene().getWindow());
        primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(UILoader.class.getResource("/lk/hostelManagement/pos/view/" + location + ".fxml")))));
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}