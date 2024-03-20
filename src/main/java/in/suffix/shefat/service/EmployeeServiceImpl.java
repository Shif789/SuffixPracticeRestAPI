package in.suffix.shefat.service;

import in.suffix.shefat.dao.IEmployeeRespository;
import in.suffix.shefat.exception.EmployeeNotFoundException;
import in.suffix.shefat.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRespository repository;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = repository.findAll();
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        //Optional<Employee> optionalEmployee = respository.findById(id);
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("employee record with id: " + id + " not found"));
        return employee;
    }

    @Override
    public String addEmployee(Employee employee) {
        Employee savedEmployee = repository.save(employee);
        if (savedEmployee!=null)
            return "Employee successfully inserted with id: "+savedEmployee.getId();

        return null;
        //return "Employee failed to be inserted with id: "+employee.getId();
    }

    @Override
    public String deleteEmployeeById(Integer id) {
        Employee employee = getEmployeeById(id);
        if (employee!=null){
            repository.deleteById(employee.getId());
            return "Employee successfully deleted with id: "+employee.getId();
        }
        throw new RuntimeException("Employee failed to be deleted with id: "+id);
    }
}
