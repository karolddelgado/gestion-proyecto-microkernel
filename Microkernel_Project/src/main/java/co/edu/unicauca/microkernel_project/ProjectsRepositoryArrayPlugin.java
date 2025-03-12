package co.edu.unicauca.microkernel_project;

import co.edu.unicauca.microkernel_common.entities.Project;
import co.edu.unicauca.microkernel_common.interfaces.IProjectRepositoryPlugin;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase `ProjectsRepositoryArrayPlugin` es una implementación de la interfaz `IProjectRepositoryPlugin`
 * que almacena proyectos en una lista en memoria. Esta clase proporciona métodos para agregar proyectos,
 * obtener la lista de proyectos y buscar un proyecto por su título.
 * 
 * @author libardo
 */
public class ProjectsRepositoryArrayPlugin implements IProjectRepositoryPlugin {

    private final List<Project> projects;
    
    /**
     * Constructor que inicializa la lista de proyectos.
     */
    public ProjectsRepositoryArrayPlugin() {
        this.projects = new ArrayList<>();
    }
  

    /**
     * Agrega un nuevo proyecto a la lista de proyectos.
     * 
     * @param proyecto El proyecto que se desea agregar.
     */
    @Override
    public void addProject(Project proyecto) {
        if (proyecto != null) {
            projects.add(proyecto);
        }
    }

    /**
     * Obtiene la lista de todos los proyectos almacenados.
     * 
     * @return Una lista de proyectos.
     */
    @Override
    public List<Project> getProjects() {
        return new ArrayList<>(projects); // Retornar una copia para evitar modificaciones externas
    }

    /**
     * Busca un proyecto por su título.
     * 
     * @param titulo El título del proyecto que se desea buscar.
     * @return El proyecto encontrado, o `null` si no se encuentra ningún proyecto con el título especificado.
     */
    @Override
    public Project findProjectByTitle(String titulo) {
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(titulo)) {
                return project;
            }
        }
        return null;
    }

    @Override
    public void initialize() {
        System.out.println("ProjectsRepositoryArrayPlugin inicializado.");
    }

    @Override
    public void shutdown() {
        System.out.println("ProjectsRepositoryArrayPlugin detenido.");
    }
}