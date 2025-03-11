
package co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines;

import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.IProjectRepositoryPlugin;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import java.util.List;

/**
 * Clase que representa un paso en la validación de registro de proyectos.
 * Verifica si un proyecto con el mismo título ya existe en el repositorio
 * antes de registrarlo.
 * 
 * Implementa la interfaz {@link PipelineStep}.
 * 
 * @author libardo
 */
public class RegisterStep implements PipelineStep {
    
    /**
     * Repositorio de proyectos donde se almacenarán los registros.
     */
    private IProjectRepositoryPlugin repositorio;

    /**
     * Constructor que inicializa el repositorio de proyectos.
     * 
     * @param repositorio el repositorio donde se almacenarán los proyectos
     */
    public RegisterStep(IProjectRepositoryPlugin repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Procesa el proyecto verificando si ya existe uno con el mismo título
     * en el repositorio. Si ya existe, lanza una excepción. Si no, lo agrega
     * al repositorio.
     * 
     * @param proyecto el proyecto a registrar
     * @return el proyecto registrado
     * @throws IllegalArgumentException si ya existe un proyecto con el mismo título
     */
    @Override
    public Project process(Project proyecto) {
        List<Project> proyectosExistentes = repositorio.getProjects();
        boolean existe = proyectosExistentes.stream()
                .anyMatch(p -> p.getTitle().equals(proyecto.getTitle()));
        
        if (existe) {
            throw new IllegalArgumentException("Ya existe un proyecto con este título.");
        }
        
        repositorio.addProject(proyecto);
        return proyecto;
    }
}
