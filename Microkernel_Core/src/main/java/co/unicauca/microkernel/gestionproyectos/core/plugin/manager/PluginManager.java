package co.unicauca.microkernel.gestionproyectos.core.plugin.manager;

import co.edu.unicauca.microkernel_common.interfaces.IProjectRepositoryPlugin;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase `PluginManager` permite registrar y administrar plugins en el sistema.
 * Los plugins deben implementar la interfaz `IProjectRepositoryPlugin` para ser registrados.
 * Esta clase mantiene una lista estática de plugins que pueden ser accedidos y gestionados
 * en cualquier momento.
 * 
 * @author libardo
 */
public class PluginManager {
    
    private static final String FILE_NAME = "plugin.properties";
    private static PluginManager instance;
    
    private static Properties pluginProperties;
    
    /**
     * Constructor por defecto de la clase `PluginManager`.
     */
    public PluginManager() {
        pluginProperties = new Properties();
    }

    // Lista estática que almacena los plugins registrados.
    private static final List<IProjectRepositoryPlugin> plugins = new ArrayList<>();

    /**
     * Registra un nuevo plugin en el sistema.
     * 
     * @param plugin El plugin que se desea registrar. Debe implementar la interfaz `IProjectRepositoryPlugin`.
     */
    public static void registerPlugin(IProjectRepositoryPlugin plugin) {
        plugins.add(plugin);
    }

    /**
     * Obtiene la lista de todos los plugins registrados en el sistema.
     * 
     * @return Una lista de plugins que implementan la interfaz `IProjectRepositoryPlugin`.
     */
    public static List<IProjectRepositoryPlugin> getPlugins() {
        return plugins;
    }
    
     public static PluginManager getInstance() {
        return instance;
    }

    public static void init(String basePath) throws Exception {

        instance = new PluginManager();
        instance.loadProperties(basePath);
        if (instance.pluginProperties.isEmpty()) {
            throw new Exception("Could not initialize plugins");
        }

    }

    public static IProjectRepositoryPlugin getPlugin(String entitie) {

        //Verificar si existe una entrada en el archivo para el país indicado.
        String propertyName = entitie.toLowerCase();
        if (!pluginProperties.containsKey(propertyName)) {
            return null;
        }

        IProjectRepositoryPlugin plugin = null;
        //Obtener el nombre de la clase del plugin.
        String pluginClassName = pluginProperties.getProperty(propertyName);

        try {

            //Obtener una referencia al tipo de la clase del plugin.
            Class<?> pluginClass = Class.forName(pluginClassName);
            if (pluginClass != null) {

                //Crear un nuevo objeto del plugin.
                Object pluginObject = pluginClass.getDeclaredConstructor().newInstance();

                if (pluginObject instanceof IProjectRepositoryPlugin) {
                    plugin = (IProjectRepositoryPlugin) pluginObject;
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            Logger.getLogger("PluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

        return plugin;
    }

    private static void loadProperties(String basePath) {

        String filePath = basePath + FILE_NAME;
        try (FileInputStream stream = new FileInputStream(filePath)) {

            pluginProperties.load(stream);

        } catch (IOException ex) {
            Logger.getLogger("PluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

    }
}
