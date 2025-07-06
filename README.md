# Sistema de Gestión de Adopciones Veterinarias

## Descripción

Sistema completo de gestión de adopciones para una clínica veterinaria, desarrollado en Java con interfaz gráfica Swing y base de datos H2.

## Características

- ✅ Sistema de autenticación de empleados
- ✅ Registro de empleados
- ✅ Registro de adopciones con validaciones
- ✅ Generación automática de tickets de adopción
- ✅ Base de datos H2 integrada
- ✅ Patrón DAO para acceso a datos
- ✅ Factory Pattern para creación de adopciones
- ✅ Template Method Pattern para proceso de adopción

## Estructura del Proyecto

```
src/main/java/
├── config/                 # Configuración de base de datos
├── controller/             # Controladores de la interfaz
├── dao/                   # Interfaces DAO
├── domain/                # Lógica de negocio
│   ├── entities/          # Entidades del dominio
│   ├── factories/         # Factories para creación de objetos
│   ├── interfaces/        # Interfaces del dominio
│   └── repositories/      # Repositorios
├── enums/                 # Enumeraciones
├── infraestructure/       # Implementaciones de infraestructura
│   ├── daos/             # Implementaciones DAO
│   ├── repositories/     # Implementaciones de repositorios
│   └── services/         # Servicios
├── org/app/              # Clase principal
├── presentation/         # Capa de presentación
└── views/               # Vistas de la interfaz
```

## Tecnologías Utilizadas

- **Java 15**
- **Maven** - Gestión de dependencias
- **H2 Database** - Base de datos embebida
- **Swing** - Interfaz gráfica
- **JUnit** - Testing

## Instalación y Ejecución

### Prerrequisitos

- Java 15 o superior
- Maven 3.6 o superior

### Pasos de Instalación

1. **Clonar el repositorio**

   ```bash
   git clone <url-del-repositorio>
   cd coreccion-parcial1-final
   ```

2. **Compilar el proyecto**

   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn exec:java -Dexec.mainClass="org.app.App"
   ```

### Credenciales de Prueba

- **Usuario:** admin
- **Contraseña:** admin

## Flujo de Uso

### 1. Login

- Al iniciar la aplicación, se muestra la pantalla de login
- Usar las credenciales de prueba: admin/admin
- O registrarse como nuevo empleado

### 2. Menú Principal

- **Nueva Adopción:** Registrar una nueva adopción
- **Cerrar Sesión:** Volver al login

### 3. Registro de Adopción

- **Datos del Adoptante:**

  - Nombre
  - Edad
  - Dirección
  - Fecha de nacimiento (formato: YYYY-MM-DD)

- **Datos de la Mascota:**

  - Nombre
  - Especie (DOG, CAT, RABBIT)
  - Fecha de nacimiento
  - Peso en kg

- **Recomendaciones automáticas** según la especie:
  - **Perro:** Paseo diario, vacunas al día, buena alimentación
  - **Gato:** Espacios tranquilos, caja de arena limpia, juego diario
  - **Otros:** Consulta con el veterinario según la especie

### 4. Ticket de Adopción

- Se genera automáticamente un ticket con toda la información
- Incluye datos del empleado, adoptante, mascota y fecha de adopción

## Patrones de Diseño Implementados

### 1. **DAO Pattern**

- Separación de la lógica de acceso a datos
- Interfaces: `AdopterDAO`, `PetDAO`, `AdoptionDAO`, `EmployeeDAO`
- Implementaciones en `infraestructure/daos/`

### 2. **Factory Pattern**

- `AdoptionFactory` crea adopciones específicas según la especie
- `DogAdoption`, `CatAdoption`, `RabbitAdoption`

### 3. **Template Method Pattern**

- Clase abstracta `Adoption` define el proceso de adopción
- Método `processAdoption()` con pasos específicos por especie

### 4. **MVC Pattern**

- **Model:** Entidades del dominio
- **View:** Interfaces Swing
- **Controller:** Controladores que conectan vista y modelo

## Base de Datos

### Tablas Creadas

- **employee:** Empleados del sistema
- **adopter:** Adoptantes registrados
- **pet:** Mascotas disponibles
- **adoption:** Registro de adopciones

### Configuración

- Base de datos H2 embebida
- Archivo: `veterinaryDB.mv.db`
- Usuario: admin
- Contraseña: admin

## Funcionalidades Técnicas

### Validaciones

- Campos obligatorios en formularios
- Validación de fechas
- Validación de tipos de datos

### Manejo de Errores

- Try-catch en operaciones de base de datos
- Mensajes de error informativos
- Validaciones de entrada

### Persistencia

- Guardado automático de adoptantes
- Guardado automático de mascotas
- Guardado automático de adopciones

## Estructura de Entidades

### Adopter (Adoptante)

- Extiende de Person
- Tiene relación con Pet y Adoption

### Pet (Mascota)

- Clase abstracta
- Implementaciones: Dog, Cat, Rabbit
- Estados de salud: Healthy, InObservation, SpecialCare

### Adoption (Adopción)

- Clase abstracta
- Implementaciones específicas por especie
- Proceso de adopción con pasos definidos

### Employee (Empleado)

- Singleton pattern para instancia única
- Autenticación por usuario y contraseña

## Comandos Útiles

### Compilación

```bash
mvn clean compile
```

### Ejecución

```bash
mvn exec:java -Dexec.mainClass="org.app.App"
```

### Testing

```bash
mvn test
```

### Package con dependencias

```bash
mvn clean package
```

## Notas de Desarrollo

- El sistema está diseñado para ser extensible
- Fácil agregar nuevas especies de mascotas
- Fácil agregar nuevos tipos de adopción
- Base de datos configurada automáticamente
- Empleado de prueba creado automáticamente

## Contribución

1. Fork el proyecto
2. Crea una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT.
