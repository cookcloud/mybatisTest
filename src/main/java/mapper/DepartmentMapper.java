package mapper;

import com.hy.mybatis.Department;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);
    public Department getDeptByIdPlus(Integer id);
    public Department getDeptByIdStep(Integer id);
}
