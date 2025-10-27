package Service.custom.impl;

import Model.Customer;
import Repository.custom.impl.customerRepositoryImpl;
import Repository.repositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl {

    public void addCustomer(Customer customer) throws SQLException {
        customerRepositoryImpl customerRepository = new customerRepositoryImpl();
        customerRepository.addCustomer(customer);
    }
    public void DeleteCustomer(Customer customer) throws SQLException {

        customerRepositoryImpl customerRepository = new customerRepositoryImpl();
        customerRepository.DeleteCustomer(customer);

    }
    public void UpdateCustomer(Customer customer) throws SQLException {
        customerRepositoryImpl customerRepository = new customerRepositoryImpl();
        customerRepository.UpdateCustomer(customer);

    }
//    public ArrayList<Customer> getAll() {
//
//    }
//



//    public List<String> getCustomerIDs() throws SQLException {
//
//       ArrayList <Customer> all = getAll();
//       List <String> custIdList=new ArrayList<>();
//
//        all.forEach(customer ->{
//            custIdList.add(customer.getId());
//
//        });
//        return custIdList;
//
//
//    }

}
