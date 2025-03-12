package co.edu.unicauca.microkernel_common.entities;

/**
 * Representa un proyecto dentro del sistema de gestión de proyectos.
 * Un proyecto tiene un título, una descripción, una empresa que lo propone,
 * un estudiante asignado (opcional) y un estado que puede ser "PROPUESTO",
 * "ASIGNADO" o "FINALIZADO".
 * 
 * @author libardo
 */
public class Project {

    private String title;
    private String description;
    private User company;
    private User student;
    private String state; // "PROPUESTO", "ASIGNADO", "FINALIZADO"

    /**
     * Constructor de la clase Project.
     * 
     * @param titulo     Título del proyecto.
     * @param descripcion Descripción del proyecto.
     * @param empresa    Empresa que propone el proyecto.
     */
    public Project(String titulo, String descripcion, User empresa) {
        this.title = titulo;
        this.description = descripcion;
        this.company = empresa;
        this.state = "PROPUESTO"; // Estado inicial
    }

    /**
     * Obtiene el título del proyecto.
     * 
     * @return Título del proyecto.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtiene la descripción del proyecto.
     * 
     * @return Descripción del proyecto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene la empresa que propuso el proyecto.
     * 
     * @return Objeto User que representa la empresa.
     */
    public User getCompany() {
        return company;
    }

    /**
     * Obtiene el estudiante asignado al proyecto, si existe.
     * 
     * @return Objeto User del estudiante asignado o null si no hay ninguno.
     */
    public User getStudent() {
        return student;
    }

    /**
     * Obtiene el estado actual del proyecto.
     * 
     * @return Estado del proyecto.
     */
    public String getState() {
        return state;
    }

    /**
     * Asigna un estudiante al proyecto y cambia su estado a "ASIGNADO".
     * 
     * @param estudiante Usuario con rol de estudiante.
     * @throws IllegalArgumentException si el usuario no es un estudiante.
     */
    public void assignStudent(User estudiante) {
        if (!"ESTUDIANTE".equals(estudiante.getRole())) {
            throw new IllegalArgumentException("Solo un estudiante puede ser asignado.");
        }
        this.student = estudiante;
        this.state = "ASIGNADO";
    }

    /**
     * Establece un nuevo título para el proyecto.
     * 
     * @param titulo Nuevo título del proyecto.
     */
    public void setTitulo(String titulo) {
        this.title = titulo;
    }

    /**
     * Representación en cadena del proyecto.
     * 
     * @return Cadena con información del proyecto.
     */
    @Override
    public String toString() {
        return "Proyecto: " + title + " | Estado: " + state
                + " | Empresa: " + company.getName()
                + (student != null ? " | Estudiante: " + student.getName() : "");
    }
}