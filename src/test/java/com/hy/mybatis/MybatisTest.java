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
     * 	原生：		Dao		====>  DaoImpl
     * 	mybatis：	Mapper	====>  xxMapper.xml
     *
     * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
     * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
     * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
     * 		（将接口和xml进行绑定）
     * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
     * 5、两个重要的配置文件：
     * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
     * 		sql映射文件：保存了每一个sql语句的映射信息：
     * 					将sql抽取出来。
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
    public void test2() throws  Exception{
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


}
