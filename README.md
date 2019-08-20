# MyBatisProject3
# Mybatis框架的输出映射类型
### 所需jar包存放在lib目录下
### Mybatis框架的输出映射类型  
  Mapper.xml映射文件中定义了操作数据库的sql，每个sql是一个statement，映射文件是mybatis的核心。  
#### resultType(输出类型)  
  * 1.输出简单类型  
    * (1)我们在CustomerMapper接口中定义查找数据库中用户总人数的方法：
    ```java
    //查询总数
    public Integer getAccountCustomer();  
    ```
    * (2)CustomerMapper.xml中的配置如下：
    ```xml
    <!-- mapper接口代理实现编写规则：
    1.映射文件中namespace要等于接口的全路径
    2.通过sql语句实现数据库的操作
    3.映射文件中sql语句id要等与于接口的方法名称
    4.映射文件中传入参数类型要等于接口方法的传入参数类型
    5.映射文件中返回结果集类型要等于接口方法的返回值类型
    -->
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="mapper.CustomerMapper">
      <!--根据ID查询客户-->
      <select id="getAccountCustomer" resultType="Integer">
        select count(*) from customer;
      </select>
    </mapper>
    ```
    * (3)测试代码：
    ```java
        @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        Integer account = customerMapper.getAccountCustomer();
        System.out.println(account);//查询总数
        sqlSession.close();
    }

    ```
#### 2.Map类型（第一种形式）:key:是列名  value:是列名对应的值
  * (1)我们在CustomerMapper接口中定义根据ID查找客户的方法
    ```java
         //根据ID查询客户返回Map类型,第一种形式
    public Map<String, Object> getCustomerByID(Integer id);
    ```
  * (2)CustomerMapper.xml中编写sql语句
    ```xml
    <select id="getCustomerByID" resultType="java.util.Map">
        select * from `customer` where cust_id=#{id}
    </select>
    ```
  * (3)测试代码
    ```java
    @Test
    public void test3(){
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        //根据ID查询客户输出Map类型，第一种形式：key:是列名  value:是列名对应的值
        Map<String, Object> customer = customerMapper.getCustomerByID(2);
        System.out.println(customer);
        sqlSession.close();
    }
    ```
#### 3.Map类型（第二种形式）:Map<key,自定义对象>，（key为自己指定的列）
  * (1)CustomerMapper接口中定义方法
     ```java
         //查询所有客户返回Map类型，第二种形式，以cust_id为key
    @MapKey("cust_id")
    public Map<Integer, Customer> getAllCustomer();
     ```
  * (2)CustomerMapper.xml中编写sql语句
    ```xml
    <select id="getAllCustomer" resultType="java.util.Map">
        select * from `customer`
    </select>
    ```
  * (3)测试代码
    ```java
    @Test
    public void test4(){
        SqlSession sqlSession = MybatisUtils.openSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        //查询所有客户返回Map类型，第二种形式：Map<key,自定义对象>，key为自己指定的列，此处为cust_id
        Map<Integer, Customer> allCustomer = customerMapper.getAllCustomer();
        System.out.println(allCustomer);
        sqlSession.close();
    }
    ```
#### 4.resultMap类型
