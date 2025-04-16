package dev.ens.projects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project save(Project project) {
       return projectRepository.save(project);
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> findAllProjectsByEmployee(int employeeId) {
        return projectRepository.findAllByEmployeeId(employeeId);
    }
}
