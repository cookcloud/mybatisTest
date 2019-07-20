package com.hy.mybatis;

import mapper.EmployeeMapper;
import mapper.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    /**
     * 功能描述:进行mybatis对数据库查询的测试
     * 1、接口式编程
     * 原生：		Dao		====>  DaoImpl
     * mybatis：	Mapper	====>  xxMapper.xml
     * <p>
     * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
     * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
     * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
     * （将接口和xml进行绑定）
     * EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
     * 5、两个重要的配置文件：
     * mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
     * sql映射文件：保存了每一个sql语句的映射信息：
     * 将sql抽取出来。
     *
     * @Author: liudongwei
     * @Date: 2019/7/20 15:44
     * @Version:
     */

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    //    没用通过mapper接口实现查询
    @Test
    public void test1() throws IOException {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        Employee employee = session.selectOne("mapper.EmployeeMapper.getEmployeeById", 1);
        System.out.println(employee);
    }

    //    通过建立mapper接口实现查询，接口式编程，这也是最常用的
    @Test
    public void test2() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
        session.close();
    }

    //没有通过sqlxml映射文件进行，而是通过直接在接口上进行写sql实现，简单的sql语句可以直接这样写
    @Test
    public void test3() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapperAnnotation EmployeeMapperAnnotation = session.getMapper(EmployeeMapperAnnotation.class);
        Employee employee = EmployeeMapperAnnotation.getEmployeeById(1);
        System.out.println(employee);
        session.close();
    }

    //对数据库的增删改进行测试
    /*
     * 1、mybatis允许增删改直接定义以下类型返回值,不需要写返回值类型，根本也写不了
     * 		Integer、Long、Boolean、void
     * 2、我们需要手动提交数据
     * 		sqlSessionFactory.openSession();===》手动提交
     * 		sqlSessionFactory.openSession(true);===》自动提交
     * */
    @Test
    public void addTest4() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//     获取到Sqlsession是不会自动提交数据的
        SqlSession session = sqlSessionFactory.openSession();

        try {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null, "jack", "jack@126.com", "0");
            Long result = employeeMapper.addEmployee(employee);
            System.out.println(result);
//          自增主键值获取
            System.out.println(employee.getId());
//            需要自己手动提交
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateTest5() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//     获取到Sqlsession是不会自动提交数据的
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(1, "jack", "jack@126.com", "0");
            Long result = employeeMapper.updateEmployee(employee);
            System.out.println(result);
//            需要自己手动提交
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteTest6() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//     获取到Sqlsession是不会自动提交数据的
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            Boolean result = employeeMapper.deleteEmploy(1);
            System.out.println(result);
//            需要自己手动提交
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
