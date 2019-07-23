package mapper;

import com.hy.mybatis.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    public Employee getEmployeeById(Integer id);

    public Long addEmployee(Employee employee);

    public Long updateEmployee(Employee employee);

    public boolean deleteEmployee(Integer id);

    //多条件查询第一种方式，进行明确指定封装参数时map的key
    public Employee getEmployeeByIdAndLastName(@Param("id")Integer id,@Param("lastName") String lastName);

    //多条件查询第二种方式，构造map
    public Employee getEmployeeByMap(Map<String, Object> map);

    //返回list，mybatis自动装成list
    public List<Employee> getEmployeeByLastNameReturnList(String lastName);

    //返回一条记录的map:key就是列名，值就是对应的值
    public Map<String, Object> getEmployeeReturnMap(Integer id);

    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("id")
    public  Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName) ;

}
