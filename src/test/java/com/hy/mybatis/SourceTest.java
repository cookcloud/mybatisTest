package com.hy.mybatis;

public class SourceTest {


    /**
     * 1、获取sqlSessionFactory对象:
     * 		解析文件的每一个信息保存在Configuration中，返回包含Configuration的DefaultSqlSession；
     * 		注意：【MappedStatement】：代表一个增删改查的详细信息
     *
     * 2、获取sqlSession对象
     * 		返回一个DefaultSQlSession对象，包含Executor和Configuration;
     * 		这一步会创建Executor对象；
     *
     * 	从源码中可以知道DefaultSqlSession是SqlSession的实例
     * 	new DefaultSqlSession(configuration, executor, autoCommit);
     * 	SqlSessionFactory和SqlSession具体创建过程中的实现类是DefaultSqlSessionFactory和DefaultSqlSession。
     *
     * 3、获取接口的代理对象（MapperProxy）
     * 		getMapper，使用MapperProxyFactory创建一个MapperProxy的代理对象
     * 		代理对象里面包含了，DefaultSqlSession（Executor）
     * 	    getMapper返回接口的代理对象，包含了SqlSession对象
     *
     * 4、执行增删改查方法
     *
     * 总结：
     * 	1、根据配置文件（全局，sql映射）初始化出Configuration对象
     * 	2、创建一个DefaultSqlSession对象，
     * 		他里面包含Configuration以及
     * 		Executor（根据全局配置文件中的defaultExecutorType创建出对应的Executor）
     *  3、DefaultSqlSession.getMapper（）：拿到Mapper接口对应的MapperProxy；
     *  4、MapperProxy里面有（DefaultSqlSession）；
     *  5、执行增删改查方法：
     *  		1）、调用DefaultSqlSession的增删改查（Executor）；
     *  		2）、会创建一个StatementHandler对象。
     *  			（同时也会创建出ParameterHandler和ResultSetHandler）
     *  		3）、调用StatementHandler预编译参数以及设置参数值;
     *  			使用ParameterHandler来给sql设置参数
     *  		4）、调用StatementHandler的增删改查方法；
     *  		5）、ResultSetHandler封装结果
     *  注意：
     *  	四大对象每个创建的时候都有一个interceptorChain.pluginAll(parameterHandler);
     *  四大对象分为为：Executor()、ParameterHandler（）、ResultSetHandler（）、ResultHandler()、StatementHandler()
     *  四大对象可以参考mybatis官方配置文件中的plugin说明：
     *  Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
     *  ParameterHandler (getParameterObject, setParameters)
     *  ResultSetHandler (handleResultSets, handleOutputParameters)
     *  StatementHandler (prepare, parameterize, batch, update, query)
     */



    /**
     * 插件原理
     * 在四大对象创建的时候
     * 1、每个创建出来的对象不是直接返回的，而是
     * 		interceptorChain.pluginAll(parameterHandler);
     * 2、获取到所有的Interceptor（拦截器）（插件需要实现的接口）；
     * 		调用interceptor.plugin(target);返回target包装后的对象
     * 3、插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面）
     * 		我们的插件可以为四大对象创建出代理对象；
     * 		代理对象就可以拦截到四大对象的每一个执行；
     *
     public Object pluginAll(Object target) {
     for (Interceptor interceptor : interceptors) {
     target = interceptor.plugin(target);
     }
     return target;
     }

     */
    /**
     * 插件编写：
     * 1、编写Interceptor的实现类（这就是所谓的插件）
     * 2、使用@Intercepts注解完成插件签名
     * 3、将写好的插件注册到全局配置文件中
     *
     */

    /*
    * 使用plugin（）方法为目标对象创建给一个代理对象
    *
    * Object proceed=invocation.proceed();执行目标方法，对目标方法进行放行，在proceed（）方法执行之前继续代理方法的增加
    * */







}
