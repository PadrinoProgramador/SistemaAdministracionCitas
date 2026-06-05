package com.tecmilenio.model;

/**
 * Clase que representa a un doctor del consultorio.
 * Hereda de la clase abstracta Usuario.
 * 
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public class Doctor extends Usuario {
    
    private String especialidad;
    
    /**
     * Constructor de Doctor.
     * 
     * @param id Identificador único del doctor
     * @param nombre Nombre completo del doctor
     * @param especialidad Especialidad médica del doctor
     */
    public Doctor(String id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }
    
    /**
     * Obtiene la especialidad del doctor.
     * 
     * @return Especialidad médica
     */
    public String getEspecialidad() {
        return especialidad;
    }
    
    /**
     * Muestra los detalles del doctor en la consola.
     */
    @Override
    public void mostrarDetalles() {
        System.out.println("=== DETALLES DEL DOCTOR ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("===========================");
    }
    
    /**
     * Convierte los datos del doctor a formato CSV.
     * Formato: id,nombre,especialidad
     * 
     * @return String en formato CSV
     */
    @Override
    public String toCSV() {
        return id + "," + nombre + "," + especialidad;
    }
    
    /**
     * Crea un objeto Doctor a partir de una línea CSV.
     * 
     * @param csvLine Línea en formato CSV
     * @return Objeto Doctor
     */
    public static Doctor fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length >= 3) {
            return new Doctor(parts[0], parts[1], parts[2]);
        }
        return null;
    }
}
