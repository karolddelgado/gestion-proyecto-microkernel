package co.unicauca.microkernel.gestionproyectos.core.domain.services.validationPipelines;

import co.edu.unicauca.microkernel_common.entities.Project;

/**
 * Clase que representa un paso de validación en un pipeline de procesamiento de proyectos.
 * <p>
 * Esta clase implementa la interfaz {@code PipelineStep} y se encarga de validar
 * que un objeto {@code Project} tenga título y descripción válidos.
 * </p>
 *
 * @author libardo
 */
public class ValidationStep implements PipelineStep {

    /**
     * Procesa y valida un proyecto dentro del pipeline de validación.
     * <p>
     * Verifica que el título y la descripción del proyecto no sean nulos ni estén vacíos.
     * Si alguna de estas condiciones no se cumple, lanza una excepción.
     * </p>
     *
     * @param proyecto el objeto {@code Project} a validar
     * @return el mismo objeto {@code Project} si pasa las validaciones
     * @throws IllegalArgumentException si el título o la descripción están vacíos o son nulos
     */
    @Override
    public Project process(Project proyecto) {
        if (proyecto.getTitle() == null || proyecto.getTitle().isBlank()) {
            throw new IllegalArgumentException("El título del proyecto no puede estar vacío.");
        }
        if (proyecto.getDescription() == null || proyecto.getDescription().isBlank()) {
            throw new IllegalArgumentException("La descripción del proyecto no puede estar vacía.");
        }
        return proyecto;
    }
}
