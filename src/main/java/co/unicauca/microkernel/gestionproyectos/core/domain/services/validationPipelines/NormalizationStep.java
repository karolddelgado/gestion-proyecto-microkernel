package co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;

/**
 * Clase que representa un paso de normalización dentro de la cadena de validación de proyectos.
 * Convierte el título del proyecto a mayúsculas para mantener consistencia en el formato.
 * 
 * @author libardo
 */
public class NormalizationStep implements PipelineStep {
    
    /**
     * Procesa el proyecto convirtiendo su título a mayúsculas.
     * 
     * @param proyecto Proyecto a normalizar.
     * @return Proyecto con el título en mayúsculas.
     * @throws IllegalArgumentException Si el proyecto es nulo.
     */
    @Override
    public Project process(Project proyecto) {
        if (proyecto == null) {
            throw new IllegalArgumentException("El proyecto no puede ser nulo");
        }
        
        String tituloNormalizado = proyecto.getTitle().toUpperCase();
        proyecto.setTitulo(tituloNormalizado);
        
        return proyecto;
    }
}