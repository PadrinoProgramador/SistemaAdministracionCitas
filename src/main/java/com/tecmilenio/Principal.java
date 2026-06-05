package com.tecmilenio;

import com.tecmilenio.model.*;
import com.tecmilenio.service.SistemaCitas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Principal que contiene el punto de entrada de la aplicación.
 * Gestiona la interfaz de usuario mediante menús en consola.
 * 
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public class Principal {
    
    private SistemaCitas sistema;
    private Scanner scanner;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    /**
     * Constructor de Principal.
     */
    public Principal() {
        this.sistema = new SistemaCitas();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Punto de entrada de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.ejecutar();
    }
    
    /**
     * Ejecuta el flujo principal de la aplicación.
     */
    private void ejecutar() {
        sistema.iniciarSistema();
        
        if (!mostrarMenuLogin()) {
            System.out.println("\n✗ Acceso denegado. El programa se cerrará.");
            return;
        }
        
        mostrarMenuPrincipal();
        System.out.println("\n✓ Gracias por usar el Sistema de Administración de Citas. ¡Hasta luego!\n");
    }
    
    /**
     * Muestra el menú de login y valida las credenciales.
     * 
     * @return true si el login fue exitoso
     */
    private boolean mostrarMenuLogin() {
        boolean autenticado = false;
        int intentos = 0;
        int maxIntentos = 3;
        
        while (!autenticado && intentos < maxIntentos) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║     LOGIN ADMINISTRADOR                ║");
            System.out.println("╚════════════════════════════════════════╝");
            
            System.out.print("Ingrese usuario: ");
            String usuario = scanner.nextLine().trim();
            
            System.out.print("Ingrese contraseña: ");
            String password = scanner.nextLine().trim();
            
            if (sistema.login(usuario, password)) {
                System.out.println("✓ Acceso concedido.\n");
                autenticado = true;
            } else {
                intentos++;
                System.out.println("✗ Credenciales incorrectas.");
                System.out.println("Intentos restantes: " + (maxIntentos - intentos) + "\n");
            }
        }
        
        return autenticado;
    }
    
    /**
     * Muestra el menú principal y gestiona las opciones del usuario.
     */
    private void mostrarMenuPrincipal() {
        int opcion = 0;
        
        while (opcion != 5) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║        MENÚ PRINCIPAL                  ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Dar de alta doctor                  ║");
            System.out.println("║ 2. Dar de alta paciente                ║");
            System.out.println("║ 3. Crear cita                          ║");
            System.out.println("║ 4. Ver citas agendadas                 ║");
            System.out.println("║ 5. Salir                               ║");
            System.out.println("╚════════════════════════════════════════╝");
            
            System.out.print("Seleccione opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1:
                        menuAltaDoctor();
                        break;
                    case 2:
                        menuAltaPaciente();
                        break;
                    case 3:
                        menuCrearCita();
                        break;
                    case 4:
                        menuVerCitas();
                        break;
                    case 5:
                        System.out.println("\n✓ Cerrando sistema...");
                        break;
                    default:
                        System.out.println("✗ Opción no válida. Intente de nuevo.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Por favor, ingrese un número válido.\n");
            }
        }
    }
    
    /**
     * Muestra el menú para dar de alta un nuevo doctor.
     */
    private void menuAltaDoctor() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        DAR DE ALTA DOCTOR              ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        System.out.print("Ingrese ID del doctor: ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Ingrese nombre completo: ");
        String nombre = scanner.nextLine().trim();
        
        System.out.print("Ingrese especialidad: ");
        String especialidad = scanner.nextLine().trim();
        
        sistema.registrarDoctor(id, nombre, especialidad);
        System.out.println();
    }
    
    /**
     * Muestra el menú para dar de alta un nuevo paciente.
     */
    private void menuAltaPaciente() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        DAR DE ALTA PACIENTE            ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        System.out.print("Ingrese ID del paciente: ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Ingrese nombre completo: ");
        String nombre = scanner.nextLine().trim();
        
        sistema.registrarPaciente(id, nombre);
        System.out.println();
    }
    
    /**
     * Muestra el menú para crear una nueva cita.
     */
    private void menuCrearCita() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           CREAR CITA                   ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        // Mostrar doctores disponibles
        List<Doctor> doctores = sistema.obtenerDoctores();
        if (doctores.isEmpty()) {
            System.out.println("✗ No hay doctores registrados. Registre un doctor primero.\n");
            return;
        }
        
        System.out.println("\nDoctores disponibles:");
        for (Doctor doctor : doctores) {
            System.out.println("  - " + doctor.getId() + ": " + doctor.getNombre() + 
                             " (" + doctor.getEspecialidad() + ")");
        }
        
        // Mostrar pacientes disponibles
        List<Paciente> pacientes = sistema.obtenerPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("✗ No hay pacientes registrados. Registre un paciente primero.\n");
            return;
        }
        
        System.out.println("\nPacientes registrados:");
        for (Paciente paciente : pacientes) {
            System.out.println("  - " + paciente.getId() + ": " + paciente.getNombre());
        }
        
        System.out.print("\nIngrese ID de la cita: ");
        String idCita = scanner.nextLine().trim();
        
        System.out.print("Ingrese fecha y hora (formato: yyyy-MM-dd HH:mm): ");
        String fechaHoraStr = scanner.nextLine().trim();
        
        LocalDateTime fechaHora;
        try {
            fechaHora = LocalDateTime.parse(fechaHoraStr, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("✗ Formato de fecha y hora inválido.\n");
            return;
        }
        
        System.out.print("Ingrese motivo de la cita: ");
        String motivo = scanner.nextLine().trim();
        
        System.out.print("Ingrese ID del doctor: ");
        String idDoctor = scanner.nextLine().trim();
        
        System.out.print("Ingrese ID del paciente: ");
        String idPaciente = scanner.nextLine().trim();
        
        sistema.agendarCita(idCita, fechaHora, motivo, idDoctor, idPaciente);
        System.out.println();
    }
    
    /**
     * Muestra todas las citas agendadas.
     */
    private void menuVerCitas() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       CITAS AGENDADAS                  ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        List<Cita> citas = sistema.obtenerCitas();
        
        if (citas.isEmpty()) {
            System.out.println("No hay citas agendadas.\n");
        } else {
            for (Cita cita : citas) {
                cita.mostrarDetalles();
            }
        }
    }
}
