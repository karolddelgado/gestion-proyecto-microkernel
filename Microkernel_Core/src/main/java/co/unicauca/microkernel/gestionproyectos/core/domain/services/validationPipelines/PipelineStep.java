package co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines;

import co.edu.unicauca.microkernel_common.entities.Project;

/**
 * Interfaz que define un contrato para los pasos que forman parte de un pipeline de procesamiento
 * en el contexto de la gestión de proyectos. Cada paso del pipeline debe implementar esta interfaz
 * para procesar un objeto de tipo {@link Project}.
 * 
 * <p>
 * Esta interfaz es utilizada en un patrón de diseño de pipeline, donde cada paso del pipeline es
 * responsable de una tarea específica (por ejemplo, validación, transformación, etc.). Cada
 * implementación de {@code PipelineStep} puede ser encadenada para formar un flujo de procesamiento
 * secuencial.
 * </p>
 * 
 * <p>
 * Cada implementación de {@code PipelineStep} debe ser independiente y enfocada en una sola
 * responsabilidad, siguiendo el principio de responsabilidad única (SRP).
 * </p>
 * 
 * @author libardo
 * @version 1.0
 * @see Project
 */
public interface PipelineStep {

    /**
     * Procesa un proyecto y lo devuelve, posiblemente con modificaciones o validaciones aplicadas.
     * Este método debe ser implementado por cualquier clase que represente un paso en el pipeline.
     * 
     * @param proyecto El proyecto a ser procesado. No debe ser {@code null}.
     * @return El proyecto procesado. Puede ser el mismo proyecto modificado o validado.
     * @throws IllegalArgumentException Si el proyecto no cumple con ciertas validaciones.
     */
    Project process(Project proyecto);
}