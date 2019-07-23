package mapper;

import com.hy.mybatis.Employee;

import java.util.List;

public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDepart(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer id);



}
