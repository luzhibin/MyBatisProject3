package test;

import domain.Customer;
import mapper.CustomerMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import utils.MybatisUtils;

import javax.sound.midi.Soundbank;
import java.util.Map;

public class Mytest {

    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        Integer account = customerMapper.getAccountCustomer();
        System.out.println(account);//查询总数
        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        Customer customer = customerMapper.getCustomerWithID(1);//根据ID查询客户
        System.out.println(customer);
        sqlSession.close();
    }

    @Test
    public void test3(){
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        //根据ID查询客户输出Map类型，第一种形式：key:是列名  value:是列名对应的值
        Map<String, Object> customer = customerMapper.getCustomerByID(2);
        System.out.println(customer);
        sqlSession.close();
    }

    @Test
    public void test4(){
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        //查询所有客户返回Map类型，第二种形式：Map<key,自定义对象>，key为自己指定的列，此处为cust_id
        Map<Integer, Customer> allCustomer = customerMapper.getAllCustomer();
        System.out.println(allCustomer);
        sqlSession.close();
    }

    @Test
    public void  test5(){
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        Customer customer = customerMapper.getCustomer(2);
        System.out.println(customer);
    }

}
