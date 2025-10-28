package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public TextField txtPasskey;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {

        if (Objects.equals(txtPasskey.getText(), "1234")){
            // Load Dashboard view
            Parent root = FXMLLoader.load(getClass().getResource("/View/Dashboad_root.fxml"));

            // Get the current stage using the login button
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Set the new scene (dashboard)
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.setTitle("BuyBerry POS Dashboard");
            stage.show();

        }
        else{
            Alert  alert =new Alert(Alert.AlertType.ERROR,"Enter Valid Passkey");
            alert.showAndWait();
        }



    }
}
