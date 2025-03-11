package co.unicauca.microkernel.gestionproyectos.core.domain.services;

import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.IProjectRepositoryPlugin;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.User;
import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.PluginManager;
import co.unicauca.microkernel.gestionproyectos.access.ProjectsRepositoryArrayPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectServiceTest {

    private ProjectService projectService;
    private IProjectRepositoryPlugin repositorio;

    @BeforeEach
    public void setUp() {
        // Inicializar el repositorio real
        repositorio = new ProjectsRepositoryArrayPlugin();

        // Registrar el repositorio en el PluginManager
        PluginManager.getPlugins().clear();
        PluginManager.registerPlugin(repositorio);

        // Crear una instancia de ProjectService
        projectService = new ProjectService();
    }

    @Test
    public void testRegisterProject() {
        // Arrange
        String title = "Sistema de Inventarios";
        String description = "Desarrollar un sistema de gestión de inventarios.";
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");

        // Act
        projectService.registerProject(title, description, empresa);

        // Assert
        List<Project> proyectos = repositorio.getProjects();
        assertEquals(1, proyectos.size(), "Debe haber un proyecto registrado");
        assertEquals(title.toUpperCase(), proyectos.get(0).getTitle(), "El título del proyecto debe coincidir");
        assertEquals(description, proyectos.get(0).getDescription(), "La descripción del proyecto debe coincidir");
        assertEquals(empresa, proyectos.get(0).getCompany(), "La empresa del proyecto debe coincidir");
    }

    @Test
    public void testListProject() {
        // Arrange
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");
        projectService.registerProject("Sistema de Inventarios", "Desarrollar un sistema de gestión de inventarios.", empresa);

        // Act
        projectService.listProject();

        // Assert
        // No hay un valor de retorno para verificar, pero podemos asegurarnos de que no haya excepciones.
        assertDoesNotThrow(() -> projectService.listProject(), "No debe lanzar excepciones al listar proyectos");
    }

    @Test
    public void testAssignProject() {
        // Arrange
        String title = "Sistema de Inventarios";
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");
        User estudiante = new User("Juan Pérez", "juan@example.com", "ESTUDIANTE");

        projectService.registerProject(title, "Desarrollar un sistema de gestión de inventarios.", empresa);

        // Act
        projectService.assignProject(title, estudiante);

        // Assert
        Project proyecto = repositorio.findProjectByTitle(title);
        assertNotNull(proyecto, "El proyecto debe existir en el repositorio");
        assertEquals(estudiante, proyecto.getStudent(), "El estudiante debe estar asignado al proyecto");
    }

    @Test
    public void testAssignProject_ProjectNotFound() {
        // Arrange
        String title = "Proyecto Inexistente";
        User estudiante = new User("Juan Pérez", "juan@example.com", "ESTUDIANTE");

        // Act
        projectService.assignProject(title, estudiante);

        // Assert
        // No hay un valor de retorno para verificar, pero podemos asegurarnos de que no haya excepciones.
        assertDoesNotThrow(() -> projectService.assignProject(title, estudiante), "No debe lanzar excepciones si el proyecto no existe");
    }
}