# Sistema de Administración de Citas Clínicas

## Acerca de

Este proyecto es una aplicación de escritorio desarrollada en **Java 11** que simula un sistema completo de administración de citas para un consultorio médico. Permite a administradores autenticados registrar doctores y pacientes, crear citas vinculando ambas entidades, y mantener la persistencia de datos en archivos CSV de forma portátil.

## Proyecto

### Descripción General

El sistema implementa una **arquitectura en 3 capas** (Modelo, Servicio, Vista) aplicando principios de programación orientada a objetos (POO) como herencia, polimorfismo, interfaces y encapsulación. La aplicación es completamente portable y puede ejecutarse en cualquier sistema operativo que tenga Java 11 instalado.

### Características Principales

- **Autenticación segura:** Login de administradores con usuario y contraseña
- **Gestión de doctores:** Registro de doctores con ID único, nombre y especialidad
- **Gestión de pacientes:** Registro de pacientes con ID único y nombre completo
- **Creación de citas:** Agendamiento de citas con fecha, hora, motivo y vinculación a doctor y paciente
- **Persistencia de datos:** Almacenamiento en archivos CSV con regeneración automática
- **Validación de datos:** Prevención de IDs duplicados y validación de campos requeridos
- **Interfaz amigable:** Menús en consola con formato visual estructurado

### Requerimientos Funcionales Implementados

| Funcionalidad | Estado |
|---------------|--------|
| Login de administrador | ✓ Implementado |
| Alta de doctores | ✓ Implementado |
| Alta de pacientes | ✓ Implementado |
| Creación de citas | ✓ Implementado |
| Asignación de citas a doctor y paciente | ✓ Implementado |
| Persistencia en archivos CSV | ✓ Implementado |
| Validación de unicidad de IDs | ✓ Implementado |
| Regeneración automática de archivos | ✓ Implementado |

## Instalación y Configuración

### Requisitos Previos

- **Java Development Kit (JDK) 11** o superior
- **Git** para clonar el repositorio (opcional)
- Cualquier sistema operativo (Windows, macOS, Linux)

### Pasos de Instalación

#### Opción 1: Desde el Repositorio (Recomendado)

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/SistemaAdministracionCitas.git
cd SistemaAdministracionCitas

# Compilar el proyecto (si necesitas modificar código)
cd src/main/java/com/tecmilenio
javac -d ../../../../out -encoding UTF-8 model/*.java persistence/*.java service/*.java *.java
cd ../../../../

# Ejecutar directamente desde el JAR
java -jar SistemaAdministracionCitas.jar
```

#### Opción 2: Desde el Código Fuente

```bash
# Descargar o clonar el repositorio
git clone https://github.com/tu-usuario/SistemaAdministracionCitas.git
cd SistemaAdministracionCitas

# Compilar todos los archivos
javac -d out -encoding UTF-8 \
  src/main/java/com/tecmilenio/model/*.java \
  src/main/java/com/tecmilenio/persistence/*.java \
  src/main/java/com/tecmilenio/service/*.java \
  src/main/java/com/tecmilenio/*.java

# Crear JAR ejecutable
jar cfe SistemaAdministracionCitas.jar com.tecmilenio.Principal -C out .

# Ejecutar
java -jar SistemaAdministracionCitas.jar
```

## Guías

### Compilación desde Línea de Comandos

Para compilar el proyecto manualmente:

```bash
# Navegar al directorio del proyecto
cd SistemaAdministracionCitas

# Crear directorio de salida
mkdir -p out

# Compilar con ECJ (Eclipse Compiler for Java)
java -cp /usr/share/java/ecj.jar org.eclipse.jdt.internal.compiler.batch.Main \
  -source 11 -target 11 -d out -encoding UTF-8 \
  src/main/java/com/tecmilenio/model/*.java \
  src/main/java/com/tecmilenio/persistence/*.java \
  src/main/java/com/tecmilenio/service/*.java \
  src/main/java/com/tecmilenio/*.java
```

### Generación de JAR Ejecutable

```bash
# Crear JAR con manifest
jar cfe SistemaAdministracionCitas.jar com.tecmilenio.Principal -C out .

# Verificar contenido del JAR
jar tf SistemaAdministracionCitas.jar
```

### Ejecución del Programa

**Desde JAR (Recomendado):**
```bash
java -jar SistemaAdministracionCitas.jar
```

**Desde clases compiladas:**
```bash
java -cp out com.tecmilenio.Principal
```

## Uso del Programa

### Flujo de Ejecución

1. **Inicio:** El programa verifica la existencia de la carpeta `db/` y crea archivos CSV si no existen.
2. **Login:** Se solicita usuario y contraseña (usuario por defecto: `admin`, contraseña: `12345`).
3. **Menú Principal:** Tras autenticación exitosa, se muestra un menú con opciones:
   - **Opción 1:** Dar de alta doctor
   - **Opción 2:** Dar de alta paciente
   - **Opción 3:** Crear cita
   - **Opción 4:** Ver citas agendadas
   - **Opción 5:** Salir

### Ejemplo de Uso Completo

```
╔════════════════════════════════════════╗
║     LOGIN ADMINISTRADOR                ║
╚════════════════════════════════════════╝
Ingrese usuario: admin
Ingrese contraseña: 12345
✓ Acceso concedido.

╔════════════════════════════════════════╗
║        MENÚ PRINCIPAL                  ║
╠════════════════════════════════════════╣
║ 1. Dar de alta doctor                  ║
║ 2. Dar de alta paciente                ║
║ 3. Crear cita                          ║
║ 4. Ver citas agendadas                 ║
║ 5. Salir                               ║
╚════════════════════════════════════════╝
Seleccione opción: 1

╔════════════════════════════════════════╗
║        DAR DE ALTA DOCTOR              ║
╚════════════════════════════════════════╝
Ingrese ID del doctor: DOC001
Ingrese nombre completo: Dr. Juan Pérez
Ingrese especialidad: Cardiología
✓ Doctor registrado exitosamente.
```

### Datos de Prueba

**Credenciales de administrador por defecto:**
- Usuario: `admin`
- Contraseña: `12345`

**Ejemplo de doctor:**
- ID: `DOC001`
- Nombre: `Dr. Juan Pérez`
- Especialidad: `Cardiología`

**Ejemplo de paciente:**
- ID: `PAC001`
- Nombre: `María García`

**Ejemplo de cita:**
- ID: `CIT001`
- Fecha y Hora: `2026-06-15 14:30`
- Motivo: `Revisión general`
- Doctor: `DOC001`
- Paciente: `PAC001`

## Estructura del Proyecto

```
SistemaAdministracionCitas/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── tecmilenio/
│                   ├── Principal.java              # Punto de entrada
│                   ├── model/
│                   │   ├── Usuario.java            # Clase abstracta base
│                   │   ├── Doctor.java             # Hereda de Usuario
│                   │   ├── Paciente.java           # Hereda de Usuario
│                   │   ├── Administrador.java      # Hereda de Usuario
│                   │   └── Cita.java               # Entidad de cita
│                   ├── service/
│                   │   └── SistemaCitas.java       # Lógica de negocio
│                   └── persistence/
│                       ├── IPersistencia.java      # Interfaz genérica
│                       └── GestorArchivosCSV.java  # Implementación CSV
├── out/                                             # Archivos compilados
├── db/                                              # Base de datos (NO en Git)
│   ├── admin.csv
│   ├── doctores.csv
│   ├── pacientes.csv
│   └── citas.csv
├── SistemaAdministracionCitas.jar                   # JAR ejecutable
├── .gitignore                                       # Archivos a ignorar
├── README.md                                        # Este archivo
└── pom.xml                                          # Configuración Maven (opcional)
```

## Arquitectura Orientada a Objetos

### Clases Principales

**Clase Abstracta `Usuario`**
- Define atributos comunes: `id`, `nombre`
- Métodos abstractos: `mostrarDetalles()`, `toCSV()`
- Aplicación de herencia y polimorfismo

**Clase `Doctor` (hereda de `Usuario`)**
- Atributo adicional: `especialidad`
- Implementa métodos abstractos

**Clase `Paciente` (hereda de `Usuario`)**
- Implementa métodos abstractos

**Clase `Administrador` (hereda de `Usuario`)**
- Atributo adicional: `password`
- Método: `validarPassword(String)`

**Clase `Cita`**
- Vincula `Doctor` y `Paciente`
- Atributos: `id`, `fechaHora`, `motivo`
- Composición de entidades

**Interfaz `IPersistencia<T>`**
- Métodos genéricos: `guardar()`, `cargar()`
- Permite múltiples implementaciones

**Clase `GestorArchivosCSV`**
- Implementa `IPersistencia<T>`
- Maneja lectura/escritura de CSV

**Clase `SistemaCitas`**
- Controlador central
- Gestiona listas en memoria
- Coordina persistencia

### Principios SOLID Aplicados

| Principio | Aplicación |
|-----------|-----------|
| **SRP** | Cada clase tiene una responsabilidad única |
| **OCP** | Interfaz `IPersistencia` permite extensión sin modificación |
| **LSP** | Subclases de `Usuario` son intercambiables |
| **ISP** | Interfaz `IPersistencia` es específica |
| **DIP** | Dependencia en interfaces, no en implementaciones |

## Créditos

- **Desarrollado por:** IOrch
- **Perfil:** Licenciado en Ciencias de la Computación
- **Materia:** Computación en Java
- **Universidad:** Tecmilenio
- **Asesor Técnico:** Manus AI - Arquitecto de Soluciones Senior en Java

## Licencia

Este proyecto se distribuye bajo la **Licencia MIT**. Ver archivo `LICENSE` para más detalles.

## Contacto y Soporte

Para reportar problemas o sugerencias, por favor abra un issue en el repositorio de GitHub.

---

**Última actualización:** Junio 2026  
**Versión:** 1.0  
**Estado:** Producción
