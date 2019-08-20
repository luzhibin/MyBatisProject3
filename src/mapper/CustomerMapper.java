package mapper;

import domain.Customer;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface CustomerMapper {
    //查询总数
    public Integer getAccountCustomer();

    //根据ID查询客户
    public Customer getCustomerWithID(Integer id);

    //根据ID查询客户返回Map类型,第一种形式
    public Map<String, Object> getCustomerByID(Integer id);

    //查询所有客户返回Map类型，第二种形式，以cust_id为key
    @MapKey("cust_id")
    public Map<Integer, Customer> getAllCustomer();

    //查询输出resultMap类型
    public Customer getCustomer(Integer id);
}
