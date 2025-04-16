package dev.ens.projects;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) {
      return projectService.save(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAllProjects() {
        return ResponseEntity.ok(projectService.findAllProjects());
    }

    @GetMapping("/employee/{employee-id}")
    public ResponseEntity<List<Project>> findAllProjectsByEmployee (
            @PathVariable ("employee-id") int employeeId
    ){
        return ResponseEntity.ok(projectService.findAllProjectsByEmployee(employeeId));
    }
}
