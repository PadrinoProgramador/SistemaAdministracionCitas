# Sistema de Administración de Citas Clínicas

## Acerca de

El Sistema de Administración de Citas Clínicas es una aplicación de consola desarrollada en **Java (JDK más reciente)** que permite gestionar las operaciones diarias de un consultorio médico. El sistema proporciona un control de acceso seguro para administradores y facilita el registro de doctores, pacientes y la programación de citas médicas. Toda la información se persiste de manera segura en archivos de texto (CSV) para garantizar la portabilidad y la integridad de los datos.

## Proyecto

El proyecto está estructurado aplicando estrictamente los principios de Programación Orientada a Objetos (POO):

### Diagrama de Flujo y Diseño
El diagrama de flujo y el diagrama de clases se encuentran detallados en el documento de reporte entregado.

### Descripción de Clases

**Clase Abstracta `Usuario`**
- **Propósito:** Servir como base para todas las entidades que representan a una persona en el sistema.
- **Variables:** `id` (String), `nombre` (String).
- **Métodos:** Getters/Setters, `mostrarDetalles()` (abstracto), `toCSV()` (abstracto).

**Clase `Doctor`**
- **Propósito:** Representar a un médico del consultorio.
- **Variables:** `especialidad` (String).
- **Métodos:** Getters/Setters, implementaciones concretas de `mostrarDetalles()` y `toCSV()`.

**Clase `Paciente`**
- **Propósito:** Representar a un paciente registrado.
- **Métodos:** Implementaciones concretas de `mostrarDetalles()` y `toCSV()`.

**Clase `Administrador`**
- **Propósito:** Manejar el acceso al sistema.
- **Variables:** `password` (String).
- **Métodos:** `validarPassword(String)`, implementaciones de métodos abstractos.

**Clase `Cita`**
- **Propósito:** Vincular a un doctor y un paciente en una fecha y hora específica.
- **Variables:** `id` (String), `fechaHora` (String), `motivo` (String), `doctor` (Doctor), `paciente` (Paciente).
- **Métodos:** Getters/Setters, `mostrarDetalles()`, `toCSV()`.

**Clase `SistemaCitas` (Servicio)**
- **Propósito:** Controlador central que maneja la lógica de negocio.
- **Variables:** Listas en memoria de doctores, pacientes y citas. Instancia de persistencia.
- **Métodos:** `altaDoctor()`, `altaPaciente()`, `crearCita()`, `mostrarCitas()`, validaciones.

**Clase `GestorArchivosCSV` (Persistencia)**
- **Propósito:** Manejar la lectura y escritura en archivos CSV.
- **Métodos:** `guardar()`, `cargar()`, validación y creación de la carpeta `db/`.

**Clase `Principal`**
- **Propósito:** Punto de entrada de la aplicación y manejo de la interfaz de usuario en consola.
- **Métodos:** `main()`, `mostrarMenuLogin()`, `mostrarMenuPrincipal()`.

## Instalación y Configuración

### Requisitos Previos
1. **JDK (Java Development Kit):** Versión más reciente (Java 21 LTS o superior).
2. **IDE:** IntelliJ IDEA Community Edition.
3. **Git:** Sistema de control de versiones.

### Configuración en IntelliJ IDEA
1. Clona el repositorio: `git clone <url-del-repositorio>`
2. Abre IntelliJ IDEA, selecciona `File -> Open` y elige la carpeta del proyecto.
3. Ve a `File -> Project Structure -> Project`.
4. En `SDK`, selecciona la versión más reciente del JDK instalada.
5. En `Language level`, selecciona la versión correspondiente al JDK.
6. IntelliJ detectará automáticamente la estructura del proyecto.
7. Asegúrate de habilitar las inspecciones de código (`Settings -> Editor -> Inspections`).
8. Configura el formateador automático (`Settings -> Editor -> Code Style -> Java`).

## Guías

### Configuración del Repositorio en GitHub (Git Flow)

El proyecto utiliza una estrategia de ramas estructurada:
1. La rama `master` contiene el código estable y las versiones finales (tags como `v1.0`).
2. La rama `develop` contiene todos los commits durante el desarrollo.
3. Cada nueva funcionalidad se desarrolla en su propia rama (ej. `crear_cita`) y luego se hace merge hacia `develop`.

### Compilación y Ejecución desde IntelliJ IDEA
1. Abre la clase `Principal.java` ubicada en `src/main/java/com/tecmilenio/`.
2. Haz clic en el ícono verde de "Run" (Play) junto al método `main`.
3. El programa se ejecutará en la consola integrada de IntelliJ.

### Compilación desde Línea de Comandos
```bash
# Navegar a la carpeta raíz del proyecto
cd SistemaAdministracionCitas

# Crear directorio de salida
mkdir -p out

# Compilar todas las clases
javac -d out src/main/java/com/tecmilenio/model/*.java \
             src/main/java/com/tecmilenio/persistence/*.java \
             src/main/java/com/tecmilenio/service/*.java \
             src/main/java/com/tecmilenio/*.java
```

### Generación de FAT JAR Ejecutable
Para crear un archivo ejecutable que contenga todas las dependencias y sea totalmente portable:
```bash
# Estando en la carpeta raíz del proyecto
jar cfe SistemaAdministracionCitas.jar com.tecmilenio.Principal -C out .
```

## Uso del programa

1. Ejecuta el archivo JAR generado:
   ```bash
   java -jar SistemaAdministracionCitas.jar
   ```
2. Al iniciar, el sistema verificará y creará la carpeta `db/` y los archivos CSV si no existen. (Nota: Esta carpeta NO se sube al repositorio).
3. Ingresa con las credenciales de administrador:
   - **Usuario:** `admin`
   - **Contraseña:** `12345`
4. En el menú principal, utiliza las opciones numéricas (1-5) para navegar:
   - **Opción 1:** Registra un nuevo doctor (ej. ID: DOC001, Nombre: Dr. Juan Pérez, Especialidad: Cardiología).
   - **Opción 2:** Registra un nuevo paciente (ej. ID: PAC001, Nombre: Carlos Martínez).
   - **Opción 3:** Crea una cita vinculando el ID del doctor y el ID del paciente registrados previamente.
   - **Opción 4:** Visualiza todas las citas agendadas con sus detalles completos.
   - **Opción 5:** Cierra el sistema de manera segura.

## Créditos
- **Desarrollado por:** IOrch - Licenciado en Ciencias de la Computación.
- **Materia:** Computación en Java - Universidad Tecmilenio.

## Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE para más detalles.
