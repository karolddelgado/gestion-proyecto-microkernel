/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.microkernel_common.interfaces;

/**
 * Interfaz base para los plugins del sistema.
 * Todos los plugins deben implementar esta interfaz para garantizar compatibilidad con el sistema.
 */
public interface IPlugin {
    /**
     * Método para inicializar el plugin cuando se carga en el sistema.
     */
    void initialize();

    /**
     * Método para detener o deshabilitar el plugin cuando se elimina del sistema.
     */
    void shutdown();
}
