package com.tecmilenio.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa una cita médica en el consultorio.
 * Vincula a un doctor con un paciente en una fecha y hora específica.
 * 
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public class Cita {
    
    private String id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    /**
     * Constructor de Cita.
     * 
     * @param id Identificador único de la cita
     * @param fechaHora Fecha y hora de la cita
     * @param motivo Motivo de la consulta
     * @param doctor Doctor asignado a la cita
     * @param paciente Paciente de la cita
     */
    public Cita(String id, LocalDateTime fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }
    
    /**
     * Obtiene el identificador de la cita.
     * 
     * @return ID de la cita
     */
    public String getId() {
        return id;
    }
    
    /**
     * Obtiene la fecha y hora de la cita.
     * 
     * @return LocalDateTime de la cita
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    /**
     * Obtiene el motivo de la cita.
     * 
     * @return Motivo de la consulta
     */
    public String getMotivo() {
        return motivo;
    }
    
    /**
     * Obtiene el doctor asignado a la cita.
     * 
     * @return Objeto Doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }
    
    /**
     * Obtiene el paciente de la cita.
     * 
     * @return Objeto Paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }
    
    /**
     * Muestra los detalles de la cita en la consola.
     */
    public void mostrarDetalles() {
        System.out.println("=== DETALLES DE LA CITA ===");
        System.out.println("ID: " + id);
        System.out.println("Fecha y Hora: " + fechaHora.format(FORMATTER));
        System.out.println("Motivo: " + motivo);
        System.out.println("Doctor: " + doctor.getNombre() + " (" + doctor.getEspecialidad() + ")");
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("===========================");
    }
    
    /**
     * Convierte los datos de la cita a formato CSV.
     * Formato: id,fechaHora,motivo,idDoctor,idPaciente
     * 
     * @return String en formato CSV
     */
    public String toCSV() {
        return id + "," + fechaHora.format(FORMATTER) + "," + motivo + "," + 
               doctor.getId() + "," + paciente.getId();
    }
    
    /**
     * Crea un objeto Cita a partir de una línea CSV.
     * Nota: Requiere acceso a las listas de doctores y pacientes para resolver referencias.
     * 
     * @param csvLine Línea en formato CSV
     * @param doctor Doctor asociado
     * @param paciente Paciente asociado
     * @return Objeto Cita
     */
    public static Cita fromCSV(String csvLine, Doctor doctor, Paciente paciente) {
        String[] parts = csvLine.split(",");
        if (parts.length >= 5) {
            LocalDateTime fechaHora = LocalDateTime.parse(parts[1], FORMATTER);
            return new Cita(parts[0], fechaHora, parts[2], doctor, paciente);
        }
        return null;
    }
}
