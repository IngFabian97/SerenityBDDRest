# Serenity BDD REST Automation Framework

[![Serenity BDD Tests](https://github.com/IngFabian97/SerenityBDDRest/actions/workflows/ci.yml/badge.svg)](https://github.com/IngFabian97/SerenityBDDRest/actions/workflows/ci.yml)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Gradle](https://img.shields.io/badge/Gradle-8.10.2-blue.svg)](https://gradle.org/)
[![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-4.2.1-green.svg)](https://serenity-bdd.info/)

Proyecto de automatización de pruebas REST API usando **Serenity BDD**, **REST Assured**, **JUnit 5** y **Gradle**.

## 🔗 Enlaces

- **Repositorio:** [https://github.com/IngFabian97/SerenityBDDRest](https://github.com/IngFabian97/SerenityBDDRest)
- **CI/CD:** [GitHub Actions](https://github.com/IngFabian97/SerenityBDDRest/actions)

## 🚀 Tecnologías

- **Java 17**
- **Gradle 8.10.2** (con Wrapper incluido)
- **Serenity BDD 4.2.1**
- **REST Assured 5.5.0**
- **JUnit 5 (Jupiter)**
- **Cucumber 7.18.1**
- **Screenplay Pattern**

## 📋 Requisitos Previos

- **Java 17** o superior instalado
- No requieres instalar Gradle (incluye Gradle Wrapper)

## 📦 Estructura del Proyecto

```
serenitybddrest/
├── build.gradle.kts              # Configuración de Gradle (Kotlin DSL)
├── settings.gradle.kts           # Configuración del proyecto
├── gradle.properties             # Propiedades y perfiles
├── gradlew                       # Wrapper de Gradle para Unix/Mac
├── gradlew.bat                   # Wrapper de Gradle para Windows
├── gradle/                       # Archivos del Gradle Wrapper
├── src/
│   ├── main/java/
│   │   └── com/example/
│   │       ├── models/           # Modelos de datos (POJOs)
│   │       │   └── users/        # Users, Datum, Support, Meta, RegisterUserInfo
│   │       ├── tasks/            # Tasks del patrón Screenplay
│   │       │   ├── GetUsers.java
│   │       │   └── RegisterUser.java
│   │       └── questions/        # Questions del patrón Screenplay
│   │           ├── GetUsersQuestion.java
│   │           └── ResponseCode.java
│   └── test/java/
│       └── SerenityInitialTest.java  # Tests de ejemplo
├── target/site/serenity/         # Reportes HTML generados
└── .github/workflows/            # CI/CD con GitHub Actions
```

## 🎯 Comandos Principales

### Compilar el proyecto
```bash
./gradlew build
```

### Ejecutar tests
```bash
# Ejecutar todos los tests
./gradlew test

# Ejecutar tests de integración
./gradlew integrationTest

# Limpiar y ejecutar tests
./gradlew clean test
```

### Generar reportes de Serenity
```bash
# Los reportes se generan automáticamente después de los tests
./gradlew aggregate

# O ejecutar tests y reportes juntos
./gradlew clean test aggregate
```

### Ver el reporte HTML
```bash
# macOS/Linux
open target/site/serenity/index.html

# Windows
start target/site/serenity/index.html
```

## 🔧 Perfiles de Entorno

El proyecto soporta diferentes perfiles configurados en `gradle.properties`:

### Perfil Dev (localhost)
```bash
./gradlew test -Pprofile=dev
```
URL base: `http://localhost:5000/api`

### Perfil Prod (default)
```bash
./gradlew test -Pprofile=prod
```
URL base: `https://reqres.in/api`

### Personalizar URL en tiempo de ejecución
```bash
./gradlew test -Prestapi.baseurl=https://mi-api.com/api
```

## 📊 Verificar Dependencias

```bash
# Ver todas las dependencias
./gradlew dependencies

# Ver solo dependencias de producción
./gradlew dependencies --configuration runtimeClasspath

# Ver solo dependencias de test
./gradlew dependencies --configuration testRuntimeClasspath
```

## 🧹 Comandos de Limpieza

```bash
# Limpiar archivos generados
./gradlew clean

# Limpiar reportes de Serenity
./gradlew clearReports

# Limpiar todo y reconstruir
./gradlew clean build
```

## 🎨 Características de Gradle

### Ejecución paralela
El proyecto está configurado para ejecutar tests en paralelo usando todos los núcleos disponibles del procesador.

### Cache
Gradle cache está habilitado para acelerar las compilaciones.

### Logs personalizados
Los tests muestran información de los tests que pasan, fallan o se saltan.

## 🔍 Características del Proyecto

### ✅ Buenas Prácticas Implementadas
- **Screenplay Pattern**: Arquitectura orientada a tareas para tests más legibles
- **Modelos con Jackson**: Deserialización automática con `@JsonProperty`
- **Separation of Concerns**: Tasks, Questions y Models separados
- **Type Safety**: Uso de genéricos y tipos específicos
- **CI/CD**: GitHub Actions con ejecución automática de tests
- **Living Documentation**: Reportes HTML detallados con Serenity

### 🎯 API Utilizada
El proyecto utiliza la API pública [ReqRes](https://reqres.in/) para demostración:
- **GET** `/users?page={page}` - Listar usuarios
- **POST** `/register` - Registrar usuario

## 📝 Ejemplo de Test

El proyecto utiliza el **Screenplay Pattern** para escribir tests más legibles y mantenibles:

```java
@ExtendWith(SerenityJUnit5Extension.class)
public class SerenityInitialTest {

    public static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void getUsersFromApi() {
        Actor fabian = Actor.named("Fabian")
            .whoCan(CallAnApi.at(BASE_URL));

        // Ejecutar la tarea de obtener usuarios de la página 2
        fabian.attemptsTo(
            GetUsers.fromPage(2)
        );

        // Verificar el código de respuesta
        fabian.should(seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200)));
   
        // Deserializar y buscar un usuario específico
        Datum user = new GetUsersQuestion().answeredBy(fabian).getData().stream()
            .filter(u -> u.getId() == 7)
            .findFirst()
            .orElse(null);

        // Verificar datos del usuario
        fabian.should(
            seeThat("Usuario no es nulo", act -> user, notNullValue()),
            seeThat("El email del usuario", act -> user.getEmail(), equalTo("michael.lawson@reqres.in")),
            seeThat("El avatar del usuario", act -> user.getAvatar(), equalTo("https://reqres.in/img/faces/7-image.jpg"))
        );
    }

    @Test
    public void registerUserTest() {
        Actor fabian = Actor.named("Fabian")
            .whoCan(CallAnApi.at(BASE_URL));

        RegisterUserInfo userInfo = new RegisterUserInfo();
        userInfo.setEmail("eve.holt@reqres.in");
        userInfo.setPassword("pistol");

        // Registrar usuario
        fabian.attemptsTo(
            RegisterUser.withInfo(userInfo)
        ); 

        // Verificar registro exitoso
        fabian.should(seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200)));
    }
}
```

### 📚 Componentes del Screenplay Pattern

#### 🎭 Tasks (Tareas)
Representan acciones de alto nivel que un actor puede realizar:
- `GetUsers.fromPage(int page)` - Obtener usuarios de una página específica
- `RegisterUser.withInfo(RegisterUserInfo info)` - Registrar un nuevo usuario

#### ❓ Questions (Preguntas)
Consultan el estado del sistema:
- `ResponseCode.was()` - Obtiene el código de respuesta HTTP
- `GetUsersQuestion` - Deserializa la respuesta a un objeto `Users`

#### 📋 Models (Modelos)
POJOs para mapear respuestas JSON con anotaciones Jackson:
- `Users` - Respuesta completa con paginación, data, support y _meta
- `Datum` - Información de un usuario individual
- `Support` - Información de soporte del API
- `Meta` - Metadatos del API ReqRes
- `RegisterUserInfo` - Datos para registro de usuario

##  Troubleshooting

### Error de permisos en gradlew
```bash
chmod +x gradlew
```

### Limpiar cache de Gradle
```bash
./gradlew clean --refresh-dependencies
```

### Ver logs detallados
```bash
./gradlew test --info
./gradlew test --debug
```

## 📚 Documentación

- [Serenity BDD](https://serenity-bdd.info/)
- [REST Assured](https://rest-assured.io/)
- [Gradle](https://docs.gradle.org/)
- [JUnit 5](https://junit.org/junit5/)

## 👤 Autor

Fabian Guarin

---

**¡Happy Testing!** 🎉
