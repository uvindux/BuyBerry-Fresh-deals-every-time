package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {
    String id;
    String name;
    String address;
    Double salary;

    public Customer(String id) {
        this.id=id;
    }
}
