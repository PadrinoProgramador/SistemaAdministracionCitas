package com.tecmilenio.model;

/**
 * Clase abstracta que representa un usuario del sistema.
 * Define los atributos y métodos comunes a todas las entidades de usuario.
 * 
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public abstract class Usuario {
    
    protected String id;
    protected String nombre;
    
    /**
     * Constructor de Usuario.
     * 
     * @param id Identificador único del usuario
     * @param nombre Nombre completo del usuario
     */
    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el identificador del usuario.
     * 
     * @return ID del usuario
     */
    public String getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre del usuario.
     * 
     * @return Nombre completo del usuario
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método abstracto que muestra los detalles del usuario.
     * Debe ser implementado por las subclases.
     */
    public abstract void mostrarDetalles();
    
    /**
     * Método abstracto que convierte el usuario a formato CSV.
     * 
     * @return String en formato CSV
     */
    public abstract String toCSV();
}
