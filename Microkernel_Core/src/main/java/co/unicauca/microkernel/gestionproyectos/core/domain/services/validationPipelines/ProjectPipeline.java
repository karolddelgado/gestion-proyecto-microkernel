package co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines;

import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un pipeline de procesamiento para proyectos. Permite agregar múltiples pasos
 * ({@link PipelineStep}) que serán ejecutados secuencialmente sobre un objeto de tipo {@link Project}.
 * 
 * <p>
 * Esta clase sigue el patrón de diseño de pipeline, donde cada paso del pipeline es responsable de una
 * tarea específica (por ejemplo, validación, transformación, etc.). Los pasos se ejecutan en el orden
 * en que fueron agregados al pipeline.
 * </p>
 * 
 * <p>
 * Ejemplo de uso:
 * <pre>
 * ProjectPipeline pipeline = new ProjectPipeline();
 * pipeline.addStep(new ValidationStep());
 * pipeline.addStep(new TransformationStep());
 * Project processedProject = pipeline.execute(project);
 * </pre>
 * </p>
 * 
 * @author libardo
 * @version 1.0
 * @see PipelineStep
 * @see Project
 */
public class ProjectPipeline {

    private List<PipelineStep> steps = new ArrayList<>();

    /**
     * Agrega un paso al pipeline. El paso será ejecutado en el orden en que fue agregado.
     * 
     * @param step El paso ({@link PipelineStep}) que se desea agregar al pipeline. No debe ser {@code null}.
     */
    public void addStep(PipelineStep step) {
        steps.add(step);
    }

    /**
     * Ejecuta todos los pasos del pipeline sobre el proyecto proporcionado. Cada paso procesa el proyecto
     * y lo pasa al siguiente paso en la secuencia.
     * 
     * @param proyecto El proyecto que se desea procesar. No debe ser {@code null}.
     * @return El proyecto procesado después de pasar por todos los pasos del pipeline.
     * @throws IllegalArgumentException Si algún paso del pipeline lanza una excepción durante el procesamiento.
     */
    public Project execute(Project proyecto) {
        for (PipelineStep step : steps) {
            proyecto = step.process(proyecto);
        }
        return proyecto;
    }
}