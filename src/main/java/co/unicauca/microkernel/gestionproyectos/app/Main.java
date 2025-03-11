package co.unicauca.microkernel.gestionproyectos.app;

import co.unicauca.microkernel.gestionproyectos.core.domain.services.ProjectService;
import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.IProjectRepositoryPlugin;
import co.unicauca.microkernel.gestionproyectos.access.ProjectsRepositoryArrayPlugin;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.User;
import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.PluginManager;

/**
 * Clase principal de la aplicación que gestiona proyectos.
 * Se encarga de inicializar el repositorio de proyectos, registrar usuarios
 * y administrar la asignación de proyectos.
 * 
 * @author libardo
 */
public class Main {

    /**
     * Método principal que ejecuta la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        
        // Inicialización del repositorio de proyectos (pendiente crear la fábrica)
        IProjectRepositoryPlugin repositorioProyectos = new ProjectsRepositoryArrayPlugin();
        
        // Registrar el plugin en el sistema
        PluginManager.registerPlugin(repositorioProyectos);
        
        // Crear instancia del servicio de proyectos
        ProjectService projectService = new ProjectService();

        // Crear usuarios
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");
        User estudiante = new User("Juan Pérez", "juan@example.com", "ESTUDIANTE");

        // Registrar un nuevo proyecto
        projectService.registerProject("Sistema de Inventarios", "Desarrollar un sistema de gestión de inventarios.", empresa);

        // Listar proyectos disponibles
        System.out.println("\nProyectos disponibles:");
        projectService.listProject();

        // Asignar un estudiante al proyecto
        projectService.assignProject("Sistema de Inventarios", estudiante);

        // Listar proyectos nuevamente para reflejar los cambios
        System.out.println("\nProyectos después de la asignación:");
        projectService.listProject();
    }
}