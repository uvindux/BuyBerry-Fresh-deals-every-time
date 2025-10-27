package Service;


import Service.custom.impl.CustomerServiceImpl;
import Service.custom.impl.ItemServiceImpl;
import utill.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    public static ServiceFactory getInstance(){
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceFactoryType(ServiceType type){
        switch (type){
            case  CUSTOMER : return (T) new CustomerServiceImpl();
            case ITEM:return (T) new ItemServiceImpl();
        }
        return null;
    }
    public void tt(){
        ServiceFactory instance1 = ServiceFactory.getInstance();
        CustomerServiceImpl serviceFactoryType = instance1.getServiceFactoryType(ServiceType.CUSTOMER);
    }
}



