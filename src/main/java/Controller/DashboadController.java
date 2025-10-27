package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboadController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    void AboutAction(ActionEvent event) {

    }

    @FXML
    void CustomerAction(ActionEvent event) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("../View/ManageCustomer.fxml"));
        assert root != null;
        root.getChildren().clear();
        root.getChildren().add(load);

    }

    @FXML
    void DashboadAction(ActionEvent event) {

    }

    @FXML
    void ItemAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../View/ManageItem.fxml"));
        assert root != null;
        root.getChildren().clear();
        root.getChildren().add(load);


    }
    @FXML
    void OderAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/oderForm.fxml"));
        assert root != null;
        root.getChildren().clear();
        root.getChildren().add(load);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
