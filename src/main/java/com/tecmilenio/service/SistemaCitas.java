package com.tecmilenio.service;

import com.tecmilenio.model.*;
import com.tecmilenio.persistence.GestorArchivosCSV;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Clase que gestiona la lógica de negocio del sistema de citas.
 * Actúa como controlador central coordinando operaciones entre modelos y persistencia.
 * 
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public class SistemaCitas {
    
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private List<Administrador> administradores;
    private GestorArchivosCSV gestorArchivos;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    /**
     * Constructor del SistemaCitas.
     */
    public SistemaCitas() {
        this.doctores = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.gestorArchivos = new GestorArchivosCSV();
    }
    
    /**
     * Inicia el sistema: inicializa archivos y carga datos en memoria.
     */
    public void iniciarSistema() {
        System.out.println("\n=== INICIANDO SISTEMA DE ADMINISTRACIÓN DE CITAS ===\n");
        gestorArchivos.inicializarArchivos();
        cargarDatosEnMemoria();
        System.out.println("\n✓ Sistema iniciado correctamente.\n");
    }
    
    /**
     * Carga todos los datos desde los archivos CSV a memoria.
     */
    private void cargarDatosEnMemoria() {
        cargarDoctores();
        cargarPacientes();
        cargarAdministradores();
        cargarCitas();
    }
    
    /**
     * Carga la lista de doctores desde el archivo CSV.
     */
    private void cargarDoctores() {
        List<String> lineas = gestorArchivos.cargar("db/doctores.csv");
        doctores.clear();
        for (String linea : lineas) {
            if (!linea.trim().isEmpty()) {
                Doctor doctor = Doctor.fromCSV(linea);
                if (doctor != null) {
                    doctores.add(doctor);
                }
            }
        }
    }
    
    /**
     * Carga la lista de pacientes desde el archivo CSV.
     */
    private void cargarPacientes() {
        List<String> lineas = gestorArchivos.cargar("db/pacientes.csv");
        pacientes.clear();
        for (String linea : lineas) {
            if (!linea.trim().isEmpty()) {
                Paciente paciente = Paciente.fromCSV(linea);
                if (paciente != null) {
                    pacientes.add(paciente);
                }
            }
        }
    }
    
    /**
     * Carga la lista de administradores desde el archivo CSV.
     */
    private void cargarAdministradores() {
        List<String> lineas = gestorArchivos.cargar("db/admin.csv");
        administradores.clear();
        for (String linea : lineas) {
            if (!linea.trim().isEmpty()) {
                Administrador admin = Administrador.fromCSV(linea);
                if (admin != null) {
                    administradores.add(admin);
                }
            }
        }
    }
    
    /**
     * Carga la lista de citas desde el archivo CSV.
     */
    private void cargarCitas() {
        List<String> lineas = gestorArchivos.cargar("db/citas.csv");
        citas.clear();
        for (String linea : lineas) {
            if (!linea.trim().isEmpty()) {
                String[] parts = linea.split(",");
                if (parts.length >= 5) {
                    Doctor doctor = buscarDoctorPorId(parts[3]);
                    Paciente paciente = buscarPacientePorId(parts[4]);
                    if (doctor != null && paciente != null) {
                        Cita cita = Cita.fromCSV(linea, doctor, paciente);
                        if (cita != null) {
                            citas.add(cita);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Valida las credenciales de un administrador.
     * 
     * @param usuario ID del usuario
     * @param password Contraseña
     * @return true si las credenciales son válidas
     */
    public boolean login(String usuario, String password) {
        for (Administrador admin : administradores) {
            if (admin.getId().equals(usuario) && admin.validarPassword(password)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Registra un nuevo doctor en el sistema.
     * 
     * @param id ID único del doctor
     * @param nombre Nombre completo del doctor
     * @param especialidad Especialidad médica
     * @return true si el registro fue exitoso
     */
    public boolean registrarDoctor(String id, String nombre, String especialidad) {
        if (buscarDoctorPorId(id) != null) {
            System.out.println("✗ Error: El ID del doctor ya existe.");
            return false;
        }
        
        if (id.trim().isEmpty() || nombre.trim().isEmpty() || especialidad.trim().isEmpty()) {
            System.out.println("✗ Error: Los datos no pueden estar vacíos.");
            return false;
        }
        
        Doctor nuevoDoctor = new Doctor(id, nombre, especialidad);
        doctores.add(nuevoDoctor);
        guardarDatos();
        System.out.println("✓ Doctor registrado exitosamente.");
        return true;
    }
    
    /**
     * Registra un nuevo paciente en el sistema.
     * 
     * @param id ID único del paciente
     * @param nombre Nombre completo del paciente
     * @return true si el registro fue exitoso
     */
    public boolean registrarPaciente(String id, String nombre) {
        if (buscarPacientePorId(id) != null) {
            System.out.println("✗ Error: El ID del paciente ya existe.");
            return false;
        }
        
        if (id.trim().isEmpty() || nombre.trim().isEmpty()) {
            System.out.println("✗ Error: Los datos no pueden estar vacíos.");
            return false;
        }
        
        Paciente nuevoPaciente = new Paciente(id, nombre);
        pacientes.add(nuevoPaciente);
        guardarDatos();
        System.out.println("✓ Paciente registrado exitosamente.");
        return true;
    }
    
    /**
     * Agenda una nueva cita en el sistema.
     * 
     * @param idCita ID único de la cita
     * @param fechaHora Fecha y hora de la cita
     * @param motivo Motivo de la consulta
     * @param idDoctor ID del doctor asignado
     * @param idPaciente ID del paciente
     * @return true si la cita fue agendada exitosamente
     */
    public boolean agendarCita(String idCita, LocalDateTime fechaHora, String motivo, 
                               String idDoctor, String idPaciente) {
        Doctor doctor = buscarDoctorPorId(idDoctor);
        Paciente paciente = buscarPacientePorId(idPaciente);
        
        if (doctor == null || paciente == null) {
            System.out.println("✗ Error: Doctor o paciente no encontrado.");
            return false;
        }
        
        if (buscarCitaPorId(idCita) != null) {
            System.out.println("✗ Error: El ID de la cita ya existe.");
            return false;
        }
        
        if (idCita.trim().isEmpty() || motivo.trim().isEmpty()) {
            System.out.println("✗ Error: Los datos no pueden estar vacíos.");
            return false;
        }
        
        Cita nuevaCita = new Cita(idCita, fechaHora, motivo, doctor, paciente);
        citas.add(nuevaCita);
        guardarDatos();
        System.out.println("✓ Cita agendada exitosamente.");
        return true;
    }
    
    /**
     * Busca un doctor por su ID.
     * 
     * @param id ID del doctor
     * @return Objeto Doctor si existe, null en caso contrario
     */
    public Doctor buscarDoctorPorId(String id) {
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }
    
    /**
     * Busca un paciente por su ID.
     * 
     * @param id ID del paciente
     * @return Objeto Paciente si existe, null en caso contrario
     */
    public Paciente buscarPacientePorId(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
    
    /**
     * Busca una cita por su ID.
     * 
     * @param id ID de la cita
     * @return Objeto Cita si existe, null en caso contrario
     */
    public Cita buscarCitaPorId(String id) {
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        return null;
    }
    
    /**
     * Obtiene la lista de doctores.
     * 
     * @return Lista de doctores
     */
    public List<Doctor> obtenerDoctores() {
        return new ArrayList<>(doctores);
    }
    
    /**
     * Obtiene la lista de pacientes.
     * 
     * @return Lista de pacientes
     */
    public List<Paciente> obtenerPacientes() {
        return new ArrayList<>(pacientes);
    }
    
    /**
     * Obtiene la lista de citas.
     * 
     * @return Lista de citas
     */
    public List<Cita> obtenerCitas() {
        return new ArrayList<>(citas);
    }
    
    /**
     * Guarda todos los datos en los archivos CSV.
     */
    private void guardarDatos() {
        guardarDoctores();
        guardarPacientes();
        guardarCitas();
    }
    
    /**
     * Guarda la lista de doctores en el archivo CSV.
     */
    private void guardarDoctores() {
        List<String> lineas = new ArrayList<>();
        for (Doctor doctor : doctores) {
            lineas.add(doctor.toCSV());
        }
        gestorArchivos.guardar(lineas, "db/doctores.csv");
    }
    
    /**
     * Guarda la lista de pacientes en el archivo CSV.
     */
    private void guardarPacientes() {
        List<String> lineas = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            lineas.add(paciente.toCSV());
        }
        gestorArchivos.guardar(lineas, "db/pacientes.csv");
    }
    
    /**
     * Guarda la lista de citas en el archivo CSV.
     */
    private void guardarCitas() {
        List<String> lineas = new ArrayList<>();
        for (Cita cita : citas) {
            lineas.add(cita.toCSV());
        }
        gestorArchivos.guardar(lineas, "db/citas.csv");
    }
}
