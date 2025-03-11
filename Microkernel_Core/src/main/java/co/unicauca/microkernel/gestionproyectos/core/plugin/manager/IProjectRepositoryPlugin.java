package co.unicauca.microkernel.gestionproyectos.core.plugin.manager;

import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import java.util.List;

/**
 * Interfaz que define el contrato para la gestión de proyectos en un repositorio.
 * Permite agregar, buscar y listar proyectos.
 * 
 * Implementaciones de esta interfaz pueden proporcionar diferentes estrategias de almacenamiento,
 * como bases de datos, archivos o servicios web.
 * 
 * @author C2T26Q3
 */

/**
 * Interfaz para los repositorios de proyectos como plugins.
 * Extiende la interfaz general de Plugin para integrarse con el sistema.
 */

public interface IProjectRepositoryPlugin extends IPlugin{

    /**
     * Agrega un nuevo proyecto al repositorio.
     * 
     * @param proyecto Objeto de tipo Project que representa el proyecto a agregar.
     */
    void addProject(Project proyecto);

    /**
     * Busca un proyecto en el repositorio por su título.
     * 
     * @param titulo Título del proyecto a buscar.
     * @return El objeto Project si se encuentra, de lo contrario, null.
     */
    Project findProjectByTitle(String titulo);

    /**
     * Obtiene la lista de todos los proyectos almacenados en el repositorio.
     * 
     * @return Lista de objetos Project.
     */
    List<Project> getProjects();
    
}
