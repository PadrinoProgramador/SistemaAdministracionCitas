package com.tecmilenio.persistence;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Clase que implementa la persistencia de datos usando archivos CSV.
 * Maneja la lectura y escritura de datos en archivos de texto plano.
 * 
 * @author IOrch - Licenciado en Ciencias de la Computación
 * @version 1.0
 */
public class GestorArchivosCSV {
    
    private static final String CARPETA_DB = "db";
    
    /**
     * Inicializa el sistema de archivos.
     * Crea la carpeta db y los archivos CSV necesarios si no existen.
     */
    public void inicializarArchivos() {
        try {
            // Crear carpeta db si no existe
            Path carpetaDb = Paths.get(CARPETA_DB);
            if (!Files.exists(carpetaDb)) {
                Files.createDirectory(carpetaDb);
                System.out.println("✓ Carpeta 'db' creada exitosamente.");
            }
            
            // Crear archivos CSV si no existen
            crearArchivoSiNoExiste(CARPETA_DB + "/doctores.csv");
            crearArchivoSiNoExiste(CARPETA_DB + "/pacientes.csv");
            crearArchivoSiNoExiste(CARPETA_DB + "/citas.csv");
            
            // Crear archivo de administrador con usuario por defecto
            Path archivoAdmin = Paths.get(CARPETA_DB + "/admin.csv");
            if (!Files.exists(archivoAdmin)) {
                Files.write(archivoAdmin, "admin,12345\n".getBytes());
                System.out.println("✓ Archivo 'admin.csv' creado con usuario por defecto.");
            }
            
        } catch (IOException e) {
            System.err.println("Error al inicializar archivos: " + e.getMessage());
        }
    }
    
    /**
     * Crea un archivo vacío si no existe.
     * 
     * @param nombreArchivo Ruta del archivo a crear
     */
    private void crearArchivoSiNoExiste(String nombreArchivo) {
        try {
            Path archivo = Paths.get(nombreArchivo);
            if (!Files.exists(archivo)) {
                Files.createFile(archivo);
                System.out.println("✓ Archivo '" + nombreArchivo + "' creado.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }
    
    /**
     * Guarda una lista de líneas en un archivo CSV.
     * 
     * @param lineas Lista de líneas a guardar
     * @param nombreArchivo Ruta del archivo
     */
    public void guardar(List<String> lineas, String nombreArchivo) {
        try {
            Path archivo = Paths.get(nombreArchivo);
            Files.write(archivo, lineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + e.getMessage());
        }
    }
    
    /**
     * Carga las líneas de un archivo CSV.
     * 
     * @param nombreArchivo Ruta del archivo
     * @return Lista de líneas del archivo
     */
    public List<String> cargar(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        try {
            Path archivo = Paths.get(nombreArchivo);
            if (Files.exists(archivo)) {
                lineas = Files.readAllLines(archivo);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar archivo: " + e.getMessage());
        }
        return lineas;
    }
}
