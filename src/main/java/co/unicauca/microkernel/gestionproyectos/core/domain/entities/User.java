
package co.unicauca.microkernel.gestionproyectos.core.domain.entities;

/**
 * Representa a los usuarios del sistema.
 * Un usuario puede tener diferentes roles dentro del sistema, como "COORDINADOR",
 * "EMPRESA" o "ESTUDIANTE".
 * 
 * @author libardo
 */
public class User {
    private String name;
    private String email;
    private String role; // "COORDINADOR", "EMPRESA", "ESTUDIANTE"

    /**
     * Constructor de la clase User.
     * 
     * @param nombre Nombre del usuario.
     * @param email Correo electrónico del usuario.
     * @param rol Rol del usuario dentro del sistema.
     */
    public User(String nombre, String email, String rol) {
        this.name = nombre;
        this.email = email;
        this.role = rol;
    }

    /**
     * Obtiene el name del usuario.
     * 
     * @return Nombre del usuario.
     */
    public String getName() { return name; }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return Correo electrónico del usuario.
     */
    public String getEmail() { return email; }

    /**
     * Obtiene el role del usuario dentro del sistema.
     * 
     * @return Rol del usuario.
     */
    public String getRole() { return role; }
}

