package co.unicauca.microkernel.gestionproyectos.core.domain.services;

import co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines.ValidationStep;
import co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines.RegisterStep;
import co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines.NormalizationStep;
import co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines.ProjectPipeline;
import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.IProjectRepositoryPlugin;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.User;
import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.PluginManager;

/**
 * Servicio para gestionar proyectos dentro del sistema.
 * Permite registrar proyectos, listarlos y asignarlos a estudiantes.
 * 
 * Utiliza un pipeline de validación, normalización y registro para garantizar
 * la correcta gestión de los proyectos.
 * 
 * @author C2T26Q3
 */
public class ProjectService {
    private IProjectRepositoryPlugin repositorio;
    private ProjectPipeline pipeline;
    
    /**
     * Constructor de ProjectService.
     * Obtiene el repositorio de proyectos desde el PluginManager y configura
     * el pipeline de procesamiento.
     */
    public ProjectService() {
        this.repositorio = PluginManager.getPlugins().get(0);
        this.pipeline = new ProjectPipeline();
        // Se agregan los pasos del pipeline
        this.pipeline.addStep(new ValidationStep());
        this.pipeline.addStep(new NormalizationStep());
        this.pipeline.addStep(new RegisterStep(repositorio));
    }

    /**
     * Registra un nuevo proyecto en el sistema.
     * 
     * @param title       Título del proyecto.
     * @param description Descripción del proyecto.
     * @param empresa     Usuario que representa la empresa que propone el proyecto.
     */
    public void registerProject(String title, String description, User empresa) {
        Project proyecto = new Project(title, description, empresa);
        try {
            pipeline.execute(proyecto);
            System.out.println("Proyecto registrado: " + proyecto.getTitle());
        } catch (Exception e) {
            System.out.println("Error al registrar el proyecto: " + e.getMessage());
        }
    }

    /**
     * Lista todos los proyectos disponibles en el sistema.
     */
    public void listProject() {
        repositorio.getProjects().forEach(System.out::println);
    }

    /**
     * Asigna un estudiante a un proyecto específico.
     * 
     * @param titulo     Título del proyecto a asignar.
     * @param estudiante Usuario que representa al estudiante.
     */
    public void assignProject(String titulo, User estudiante) {
        Project proyecto = repositorio.findProjectByTitle(titulo);
        if (proyecto == null) {
            System.out.println("Proyecto no encontrado.");
            return;
        }
        proyecto.assignStudent(estudiante);
        System.out.println("Proyecto asignado a: " + estudiante.getName());
    }
}

