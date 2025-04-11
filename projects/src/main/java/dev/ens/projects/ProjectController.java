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
    public void createProject(@RequestBody Project project) {
        projectService.save(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAllProjects() {
        return ResponseEntity.ok(projectService.findAllProjects());
    }
}
