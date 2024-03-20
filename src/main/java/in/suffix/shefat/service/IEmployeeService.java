package in.suffix.shefat.service;

import in.suffix.shefat.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    String addEmployee(Employee employee);

    String deleteEmployeeById(Integer id);
}
