package mapper;

import com.hy.mybatis.Employee;

public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDepart(Integer id);

    public Employee getEmpByIdStep(Integer id);
}
