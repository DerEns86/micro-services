package dev.ens.employees;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class EmployeeIntegrationTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    void testCreateEmployeeAndGetAll() {
        // given
        Employee employee = new Employee();
        employee.setName("Max Mustermann");
        employee.setEmail("max@example.com");
        employee.setPhone("0001");
        employee.setAddress("Musterstraße 1");
        employee.setPosition("Developer");
        employee.setAssignedProjectId(1);

        // when: POST
        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(
                "/api/v1/employees", employee, Employee.class
        );

        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Employee created = postResponse.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Max Mustermann");

        // when: GET
        ResponseEntity<Employee[]> getResponse = restTemplate.getForEntity(
                "/api/v1/employees", Employee[].class
        );

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Employee[] employees = getResponse.getBody();
        assertThat(employees).isNotNull();
        assertThat(employees.length).isEqualTo(1);
        assertThat(employees[0].getEmail()).isEqualTo("max@example.com");
    }
}
