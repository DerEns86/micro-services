package dev.ens.employees.client;

import dev.ens.employees.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "project-service", url = "${application.config.projects-url}")
public interface ProjectClient {

    @GetMapping("/employee/{employee-id}")
    List<Project> findAllProjectsByEmployee (@PathVariable("employee-id") int employeeId);
}
