package com.tecmilenio.persistence;

import java.util.List;

/**
 * Interfaz genérica que define el contrato para operaciones de persistencia.
 * Permite implementar diferentes estrategias de almacenamiento (CSV, JSON, SQL, etc.)
 * sin afectar el resto del sistema.
 * 
 * @param <T> Tipo de entidad a persistir
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public interface IPersistencia<T> {
    
    /**
     * Guarda una lista de entidades en el almacenamiento.
     * 
     * @param entidades Lista de entidades a guardar
     * @param nombreArchivo Nombre del archivo donde guardar
     */
    void guardar(List<T> entidades, String nombreArchivo);
    
    /**
     * Carga una lista de entidades desde el almacenamiento.
     * 
     * @param nombreArchivo Nombre del archivo a cargar
     * @return Lista de entidades cargadas
     */
    List<T> cargar(String nombreArchivo);
}
