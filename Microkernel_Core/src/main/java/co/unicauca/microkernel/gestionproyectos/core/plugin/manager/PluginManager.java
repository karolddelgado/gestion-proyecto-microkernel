package co.unicauca.microkernel.gestionproyectos.core.plugin.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase `PluginManager` permite registrar y administrar plugins en el sistema.
 * Los plugins deben implementar la interfaz `IProjectRepositoryPlugin` para ser registrados.
 * Esta clase mantiene una lista estática de plugins que pueden ser accedidos y gestionados
 * en cualquier momento.
 * 
 * @author libardo
 */
public class PluginManager {

    /**
     * Constructor por defecto de la clase `PluginManager`.
     */
    public PluginManager() {
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
}
