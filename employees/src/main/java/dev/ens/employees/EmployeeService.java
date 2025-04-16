package dev.ens.employees;

import dev.ens.employees.client.ProjectClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectClient projectClient;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(@RequestBody Employee employee) {
       return employeeRepository.save(employee);
    }

    public FullEmployeeResponse findEmployeesWithStudents(int employeeId) {
        var employee = employeeRepository.findById(employeeId).orElse(Employee.builder()
                .name("NOT_FOUND")
                .email("NOT_FOUND")
        .phone("NOT_FOUND")
        .address("NOT_FOUND")
        .position("NOT_FOUND")
                .build());

        var projects = projectClient.findAllProjectsByEmployee(employeeId);

        return FullEmployeeResponse.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .address(employee.getAddress())
                .position(employee.getPosition())
                .projects(projects)
                .build();
    }
}
