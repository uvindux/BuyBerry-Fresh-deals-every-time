package Service.custom;

import Model.Customer;
import Model.Item;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    List<String> getCustIds();
}
