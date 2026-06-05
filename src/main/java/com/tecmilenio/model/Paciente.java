package com.tecmilenio.model;

/**
 * Clase que representa a un paciente del consultorio.
 * Hereda de la clase abstracta Usuario.
 * 
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public class Paciente extends Usuario {
    
    /**
     * Constructor de Paciente.
     * 
     * @param id Identificador único del paciente
     * @param nombre Nombre completo del paciente
     */
    public Paciente(String id, String nombre) {
        super(id, nombre);
    }
    
    /**
     * Muestra los detalles del paciente en la consola.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("=== DETALLES DEL PACIENTE ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("=============================");
    }
    
    /**
     * Convierte los datos del paciente a formato CSV.
     * Formato: id,nombre
     * 
     * @return String en formato CSV
     */
    @Override
    public String toCSV() {
        return id + "," + nombre;
    }
    
    /**
     * Crea un objeto Paciente a partir de una línea CSV.
     * 
     * @param csvLine Línea en formato CSV
     * @return Objeto Paciente
     */
    public static Paciente fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length >= 2) {
            return new Paciente(parts[0], parts[1]);
        }
        return null;
    }
}
