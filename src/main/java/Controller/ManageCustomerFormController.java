package Controller;

import DB.DBConnection;
import Model.Customer;
import Service.custom.impl.CustomerServiceImpl;
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

public class ManageCustomerFormController implements Initializable {

    @FXML
    private TableColumn colCustomerId;

    @FXML
    private TableColumn colCustomerName;

    @FXML
    private TableColumn colCustomerSalary;

    @FXML
    private TableColumn colCustomerAddress;

    @FXML
    private TableView tblCustomers;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerSalary;

    @FXML
    void btnOnActionAddCustomer(ActionEvent event) throws SQLException {
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        Customer customer1 = new Customer(txtCustomerId.getText(),txtCustomerName.getText(),txtCustomerAddress.getText(),Double.parseDouble(txtCustomerSalary.getText()));
        customerService.addCustomer(customer1);


        loadTable();
        restTextFields();
    }

    @FXML
    void btnOnActionLoadTable(ActionEvent event) {
        loadTable();
    }

    public void loadTable() {
        ArrayList<Customer> customerArrayList = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(connection);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getDouble(4));
                System.out.println(customer);
                customerArrayList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblCustomers.setItems(FXCollections.observableArrayList(customerArrayList));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustomerSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    // Delete
    public void btnOnActionDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        Customer customer = new Customer(txtCustomerId.getText());
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.DeleteCustomer(customer);

        loadTable();
        restTextFields();
    }

    // Update
    public void btnOnActionUpdateCustomer(ActionEvent actionEvent) throws SQLException {
        Customer customer1 = new Customer(txtCustomerId.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                Double.parseDouble(txtCustomerSalary.getText()));

        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.UpdateCustomer(customer1);


        loadTable();
        restTextFields();
    }

    // Search
    public void btnOnActionSearchCustomer(ActionEvent actionEvent) throws SQLException {
        ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement("Select * From Customer where id='" + txtCustomerId.getText() + "'").executeQuery();

        if (resultSet.next()){
            String id = resultSet.getString("id");    // It's working but there is an issue.
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            double salary = resultSet.getDouble("salary");
            Customer customer = new Customer(id, name, address, salary);
            if (customer!=null){
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerSalary.setText(""+customer.getSalary());
            }
        }
    }

// Resets Text Fields
    public void restTextFields(){
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerSalary.setText("");
    }
}
