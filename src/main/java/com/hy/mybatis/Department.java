package com.hy.mybatis;

import java.util.List;

public class Department {
    private Integer id;
    private String departname;
    private List<Employee> emps;

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departname='" + departname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public Department(Integer id, String departname) {

        this.id = id;
        this.departname = departname;
    }

    public Department() {

    }
}
