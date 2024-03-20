package in.suffix.shefat.controller;

import in.suffix.shefat.model.Employee;
import in.suffix.shefat.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    // GET All Employees :: http://localhost:9999/api/v1/employee/searchAllEmployees
    @GetMapping(value = "/searchAllEmployees")
    public ResponseEntity<List<Employee>> searchAllEmployees() {
        List<Employee> allEmployeesList = service.getAllEmployees();
        ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<>(allEmployeesList, HttpStatus.OK);
        return responseEntity;
    }

    // GET Employee By Id :: http://localhost:9999/api/v1/employee/searchEmployee/1
    @GetMapping(value = "/searchEmployee/{id}")
    public ResponseEntity<?> searchEmployeeById(@PathVariable Integer id) {
        Employee employee = service.getEmployeeById(id);
        ResponseEntity<Employee> responseEntity = new ResponseEntity<>(employee, HttpStatus.OK);
        return responseEntity;
    }

    // SAVE Employee :: http://localhost:9999/api/v1/employee/saveEmployee
    @PostMapping(value = "/saveEmployee")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
        String message = service.addEmployee(employee);
        ResponseEntity<String> responseEntity = null;

        if (message != null) {
            responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
            return responseEntity;
        }

        responseEntity = new ResponseEntity<>("Employee failed to be inserted with id: " + employee.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;

    }

    // UPDATE Employee :: http://localhost:9999/api/v1/employee/updateEmployee/2
    @PutMapping(value = "/updateEmployee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        Employee updatingEmployee = service.getEmployeeById(id);

        ResponseEntity<String> responseEntity = null;

        if (updatingEmployee != null) {
            updatingEmployee.setFirstName(employee.getFirstName());
            updatingEmployee.setLastName(employee.getLastName());
            updatingEmployee.setAge(employee.getAge());
            updatingEmployee.setEmail(employee.getEmail());
            updatingEmployee.setAddress(employee.getAddress());
            String message = service.addEmployee(updatingEmployee);

            if (message != null) {
                message="Employee successfully updated with id: "+updatingEmployee.getId();
                responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
                return responseEntity;
            } else
                throw new RuntimeException("Employee record found with id: " + updatingEmployee.getId() + ", however updation failed");
        }


        responseEntity = new ResponseEntity<>("Employee values is null with id: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;

    }

    // DELETE Employee :: http://localhost:9999/api/v1/employee/deleteEmployee/2
    @DeleteMapping(value = "/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        String message=null;
        message = service.deleteEmployeeById(id);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
        return  responseEntity;
    }
}
