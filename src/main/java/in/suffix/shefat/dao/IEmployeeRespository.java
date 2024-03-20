package in.suffix.shefat.dao;

import in.suffix.shefat.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRespository extends JpaRepository<Employee,Integer> {
}
