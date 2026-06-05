package com.tecmilenio.model;

/**
 * Clase que representa a un administrador del sistema.
 * Hereda de la clase abstracta Usuario.
 * 
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public class Administrador extends Usuario {
    
    private String password;
    
    /**
     * Constructor de Administrador.
     * 
     * @param id Identificador único del administrador
     * @param nombre Nombre completo del administrador
     * @param password Contraseña del administrador
     */
    public Administrador(String id, String nombre, String password) {
        super(id, nombre);
        this.password = password;
    }
    
    /**
     * Valida la contraseña ingresada.
     * 
     * @param passwordIngresada Contraseña a validar
     * @return true si la contraseña es correcta, false en caso contrario
     */
    public boolean validarPassword(String passwordIngresada) {
        return this.password.equals(passwordIngresada);
    }
    
    /**
     * Muestra los detalles del administrador en la consola.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("=== DETALLES DEL ADMINISTRADOR ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("==================================");
    }
    
    /**
     * Convierte los datos del administrador a formato CSV.
     * Formato: id,password
     * 
     * @return String en formato CSV
     */
    @Override
    public String toCSV() {
        return id + "," + password;
    }
    
    /**
     * Crea un objeto Administrador a partir de una línea CSV.
     * 
     * @param csvLine Línea en formato CSV
     * @return Objeto Administrador
     */
    public static Administrador fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length >= 2) {
            return new Administrador(parts[0], parts[0], parts[1]);
        }
        return null;
    }
}
