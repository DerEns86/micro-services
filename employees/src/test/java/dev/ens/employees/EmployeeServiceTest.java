package dev.ens.employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {


    //creates a mock-Object
    @Mock
    private EmployeeRepository employeeRepository;

    // creates a Instance of the Service and injects the mock-Onject (employeeRepo)
    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void findAllEmployees_shouldReturnAllEmployeesAsList() {

        //GIVEN
        Employee employee1 = new Employee(1, "First Employee", "employee1@mail.com", "0001", "Test Address1", "Position1", 1);
        Employee employee2 = new Employee(2, "Second Employee", "employee2@mail.com", "0002", "Test Address2", "Position2", 1);
        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));

        //WHEN
        List<Employee> employees = employeeService.findAllEmployees();

        //THEN
        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("First Employee", employees.get(0).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findAllEmployees_shouldReturnEmptyList(){
        //GIVEN
        when(employeeRepository.findAll()).thenReturn(List.of());

        //WHEN
        List<Employee> employees = employeeService.findAllEmployees();

        //THEN
        assertNotNull(employees);
        assertEquals(0, employees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void createEmployee() {
        //GIVEN
        Employee employeeToSave = new Employee(1, "First Employee", "employee1@mail.com", "0001", "Test Address1", "Position1", 1);
        when(employeeRepository.save(employeeToSave)).thenReturn(employeeToSave);

        //WHEN
        Employee savedEmployee = employeeService.createEmployee(employeeToSave);

        //THEN
        assertNotNull(savedEmployee);
        assertEquals("First Employee", savedEmployee.getName());
        assertEquals(1, savedEmployee.getId());
        verify(employeeRepository, times(1)).save(employeeToSave);

    }
}