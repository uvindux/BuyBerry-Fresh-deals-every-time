package Controller;

import Service.ServiceFactory;
import Service.custom.CustomerService;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;
import utill.ServiceType;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ManageOrdersFormController implements Initializable {

    public Label lblTime;
    @FXML
    private Button btnCart;

    @FXML
    private Button btnPlaceOder;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colTot;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<?> tblCard;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private ComboBox<?> txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerSalary;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private ComboBox<?> txtItemCode;

    @FXML
    private Label txtNetTot;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void BtnPlacHolderOnAction(ActionEvent event) {

    }

    @FXML
    void onActionCart(ActionEvent event) {


    }
    private  void loadDataTime(){
        Date date =new Date ();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        lblDate.setText(simpleDateFormat.format(date));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,e -> {
                    LocalTime now =LocalTime.now();
                    lblTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());

                }),
                new KeyFrame(Duration.seconds(1))



        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
//    CustomerService customerService= ServiceFactory.getInstance().getServiceFactoryType(ServiceType.CUSTOMER);
//
//    private void loadCustomerIDs(){
//       customerService.getCustIds();
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataTime();
    }


}
