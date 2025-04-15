package dev.ens.projects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void save_shouldSaveProject() {
        //GIVEN
        Project newProject = new Project(1,"Test Project", "Test Costumer", 1);
        when(projectRepository.save(newProject)).thenReturn(newProject);

        //WHEN
        projectService.save(newProject);

        //THEN
        verify(projectRepository).save(newProject);

    }

    @Test
    void findAllProjects_shouldReturnAllProjects() {
        //GIVEN
        Project project1 = new Project(1,"Test Project", "Test Costumer", 1);
        Project project2 = new Project(2,"Test Project 2", "Test Costumer 2", 1);
        when(projectRepository.findAll()).thenReturn(List.of(project1, project2));

        //WHEN
        List<Project> projects = projectService.findAllProjects();

        //THEN
        assertEquals(2, projects.size());
        assertEquals("Test Project", projects.get(0).getName());
        assertEquals("Test Costumer 2", projects.get(1).getCustomer());
        assertNotNull(projects);
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void findAllProjects_shouldReturnEmptyList() {
        //GIVEN
        when(projectRepository.findAll()).thenReturn(List.of());

        //WHEN
        List<Project> projects = projectService.findAllProjects();

        //THEN
        assertEquals(0, projects.size());
        verify(projectRepository, times(1)).findAll();
    }
}