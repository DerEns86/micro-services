package dev.ens.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping("/projects/{employee-id}")
    public ResponseEntity<FullEmployeeResponse> getEmployees(@PathVariable("employee-id") int employeeId) {
        return ResponseEntity.ok(employeeService.findEmployeesWithStudents(employeeId));
    }
}
