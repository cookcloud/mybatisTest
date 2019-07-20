package mapper;

import com.hy.mybatis.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {

    @Select("select * from  employee where id = #{id}")
    public Employee getEmployeeById(Integer id);
}
