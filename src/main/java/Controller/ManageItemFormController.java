package Controller;

import DB.DBConnection;
import Model.Item;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageItemFormController implements Initializable {

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colItemDescription;

    @FXML
    private TableColumn colItemUnitPrice;

    @FXML
    private TableColumn colQtyOnHand;

    @FXML
    private TableView tblItems;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtItemDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    //Add
    @FXML
    void btnOnActionAddItem(ActionEvent event) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
        Item item = new Item(
                txtItemCode.getText(),
                txtItemDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        preparedStatement.setString(1, item.getCode());
        preparedStatement.setString(2, item.getDescription());
        preparedStatement.setDouble(3, item.getUnitPrice());
        preparedStatement.setInt(4, item.getQtyOnHand());

        preparedStatement.executeUpdate();
        loadTable();
        restTextFields();
    }

    //Delete
    @FXML
    void btnOnActionDeleteItem(ActionEvent event) throws SQLException {
        DBConnection.getInstance().getConnection().prepareStatement("Delete From Item where code='" + txtItemCode.getText() + "'").executeUpdate();
        loadTable();
        restTextFields();
    }

    //Search
    @FXML
    void btnOnActionSearchItem(ActionEvent event) throws SQLException {
        ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement("Select * From Item where code='" + txtItemCode.getText() + "'").executeQuery();

        if (resultSet.next()) {
            String code = resultSet.getString("code");
            String description = resultSet.getString("description");
            Double unitPrice = resultSet.getDouble("unitPrice");
            Integer qtyOnHand = resultSet.getInt("qtyOnHand");
            Item item = new Item(code, description, unitPrice, qtyOnHand);
            if (item!=null){
                txtItemDescription.setText(item.getDescription());
                txtUnitPrice.setText(""+item.getUnitPrice());
                txtQtyOnHand.setText(""+item.getQtyOnHand());
            }
        }
    }

    //Update
    @FXML
    void btnOnActionUpdateItem(ActionEvent event) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("Update Item set description=?, unitPrice=?, qtyOnHand=? where code=?");
        Item item = new Item(txtItemCode.getText(),
                txtItemDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        preparedStatement.setObject(1,item.getDescription());
        preparedStatement.setObject(2,item.getUnitPrice());
        preparedStatement.setObject(3,item.getQtyOnHand());
        preparedStatement.setObject(4,item.getCode());
        preparedStatement.executeUpdate();

        loadTable();
        restTextFields();
    }

    //LoadTable
    @FXML
    void btnOnActionLoadTable(ActionEvent event) {
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
    }

    //LoadTable
    public void loadTable() {
        ArrayList<Item> itemsArrayList = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(connection);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Item");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Item item = new Item(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3),resultSet.getInt(4));
                System.out.println(item);
                itemsArrayList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblItems.setItems(FXCollections.observableArrayList(itemsArrayList));
    }

    // Resets Text Fields
    public void restTextFields(){
        txtItemCode.setText("");
        txtItemDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

}
