package in.suffix.shefat.advice;

import in.suffix.shefat.error.EmployeeErrorDetails;
import in.suffix.shefat.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EmployeeErrorControllerAdvice {

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<EmployeeErrorDetails> handleEmployeeException(EmployeeNotFoundException e){
        EmployeeErrorDetails employeeErrorDetails = new EmployeeErrorDetails(LocalDateTime.now(), e.getMessage(), "404- Not Found");
        ResponseEntity<EmployeeErrorDetails> responseEntity = new ResponseEntity<>(employeeErrorDetails, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<EmployeeErrorDetails> handleException(Exception e){
        EmployeeErrorDetails employeeErrorDetails = new EmployeeErrorDetails(LocalDateTime.now(), e.getMessage(), "problem in execution");
        ResponseEntity<EmployeeErrorDetails> responseEntity = new ResponseEntity<>(employeeErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
