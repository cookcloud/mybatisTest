package mapper;

import com.hy.mybatis.Employee;

public interface EmployeeMapper {
    public Employee getEmployeeById(Integer id);

    public Long addEmployee(Employee employee);

    public Long updateEmployee(Employee employee);

    public boolean deleteEmploy(Integer id);
}
