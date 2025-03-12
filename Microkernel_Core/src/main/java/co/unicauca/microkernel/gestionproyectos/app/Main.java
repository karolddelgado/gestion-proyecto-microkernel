package co.unicauca.microkernel.gestionproyectos.app;

import co.unicauca.microkernel.gestionproyectos.core.domain.services.ProjectService;
import co.edu.unicauca.microkernel_project.ProjectsRepositoryArrayPlugin;
import co.edu.unicauca.microkernel_common.entities.User;
import co.edu.unicauca.microkernel_common.interfaces.IProjectRepositoryPlugin;
import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.PluginManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal de la aplicación que gestiona proyectos.
 * Se encarga de inicializar el repositorio de proyectos, registrar usuarios
 * y administrar la asignación de proyectos.
 * 
 * @author libardo
 */
public class Main {
    
    
    /**
     * Método principal que ejecuta la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) throws Exception {
        
        //Inicializar el plugin manager con la ruta base de la aplicación.
        String basePath = getBaseFilePath();
        try {
            PluginManager.init(basePath);

        } catch (Exception ex) {
            Logger.getLogger("Application").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }
        
        
        
        
        // Inicialización del repositorio de proyectos (pendiente crear la fábrica)
        IProjectRepositoryPlugin repositorioProyectos = new ProjectsRepositoryArrayPlugin();
        
        // Registrar el plugin en el sistema
        PluginManager.registerPlugin(repositorioProyectos);
        
        // Crear instancia del servicio de proyectos
        ProjectService projectService = new ProjectService();

        // Crear usuarios
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");
        User estudiante = new User("Juan Pérez", "juan@example.com", "ESTUDIANTE");

        // Registrar un nuevo proyecto
        projectService.registerProject("Sistema de Inventarios", "Desarrollar un sistema de gestión de inventarios.", empresa);

        // Listar proyectos disponibles
        System.out.println("\nProyectos disponibles:");
        projectService.listProject();

        // Asignar un estudiante al proyecto
        projectService.assignProject("Sistema de Inventarios", estudiante);

        // Listar proyectos nuevamente para reflejar los cambios
        System.out.println("\nProyectos después de la asignación:");
        projectService.listProject();
    }
    
    /**
     * Obtiene la ruta base donde está corriendo la aplicación, sin importar que
     * sea desde un archivo .class o desde un paquete .jar.
     *
     */
    private static String getBaseFilePath() {
        try {
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8"); //This should solve the problem with spaces and special characters.
            File pathFile = new File(path);
            if (pathFile.isFile()) {
                path = pathFile.getParent();
                
                if (!path.endsWith(File.separator)) {
                    path += File.separator;
                }
                
            }

            return path;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error al eliminar espacios en la ruta del archivo", ex);
            return null;
        }
    }
}