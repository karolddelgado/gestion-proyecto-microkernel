package co.unicauca.microkernel.gestionproyectos.access;

import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.IProjectRepositoryPlugin;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de IProjectRepositoryPlugin que almacena los proyectos en una base de datos.
 */
public class ProjectsRepositoryDatabasePlugin implements IProjectRepositoryPlugin {
    private Connection connection;

    @Override
    public void initialize() {
        try {
            // Conectar a la base de datos (Ajusta la URL, usuario y contraseña según tu configuración)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectos_db", "user", "password");
            System.out.println("ProjectsRepositoryDatabasePlugin inicializado y conectado a la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("ProjectsRepositoryDatabasePlugin desconectado de la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProject(Project proyecto) {
        String sql = "INSERT INTO proyectos (titulo, descripcion, empresa, estudiante, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, proyecto.getTitle());
            stmt.setString(2, proyecto.getDescription());
            stmt.setString(3, proyecto.getCompany());
            stmt.setString(4, proyecto.getStudentAssigned());
            stmt.setString(5, proyecto.getState().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM proyectos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Project project = new Project(
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("empresa"),
                    rs.getString("estudiante"),
                    Project.State.valueOf(rs.getString("estado"))
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Project findProjectByTitle(String titulo) {
        String sql = "SELECT * FROM proyectos WHERE titulo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Project(
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("empresa"),
                        rs.getString("estudiante"),
                        Project.State.valueOf(rs.getString("estado"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Asigna un proyecto a un estudiante y actualiza su estado a "ASIGNADO".
     *
     * @param titulo    Título del proyecto a asignar.
     * @param estudiante Nombre del estudiante asignado.
     */
    public void assignProject(String titulo, String estudiante) {
        String sql = "UPDATE proyectos SET estudiante = ?, estado = ? WHERE titulo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estudiante);
            stmt.setString(2, "ASIGNADO");
            stmt.setString(3, titulo);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proyecto '" + titulo + "' asignado a " + estudiante);
            } else {
                System.out.println("No se encontró el proyecto con título: " + titulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}