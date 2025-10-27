package Repository.custom.impl;

import DB.DBConnection;
import Model.Customer;
import Repository.custom.customerRepository;
import lombok.SneakyThrows;
import utill.CrudUtill;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class customerRepositoryImpl  {

    public void addCustomer(Customer customer) throws SQLException {
        CrudUtill.execute("INSERT INTO Customer VALUES(?,?,?,?)",customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
//
//        preparedStatement.setString(1, customer.getId());
//        preparedStatement.setString(2, customer.getName());
//        preparedStatement.setString(3, customer.getAddress());
//        preparedStatement.setDouble(4, customer.getSalary());

    }





    public void DeleteCustomer(Customer customer) throws SQLException {
        DBConnection.getInstance().getConnection().prepareStatement("Delete From Customer where id='" + customer.getId() + "'").executeUpdate();

    }
    public void UpdateCustomer(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("Update Customer set name=?, address=?, salary=? where id=?");


        preparedStatement.setObject(1,customer.getName());
        preparedStatement.setObject(2,customer.getAddress());
        preparedStatement.setObject(3,customer.getSalary());
        preparedStatement.setObject(4,customer.getId());

        preparedStatement.executeUpdate();

    }
}
