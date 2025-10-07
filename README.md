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
├── serenity.properties           # Configuración de Serenity BDD y Cucumber
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
│   │       ├── questions/        # Questions del patrón Screenplay
│   │       │   ├── GetUsersQuestion.java
│   │       │   └── ResponseCode.java
│   │       ├── interactions/     # Interacciones personalizadas
│   │       │   └── Post.java
│   │       └── stepdefinitios/   # Step Definitions de Cucumber
│   │           └── RegisterUserSteps.java
│   ├── test/java/
│   │   ├── com/example/runners/
│   │   │   └── CucumberTestSuite.java  # Runner de Cucumber
│   │   └── SerenityInitialTest.java    # Tests JUnit 5
│   └── test/resources/
│       └── features/             # Archivos .feature de Cucumber
│           └── register_users.feature
├── target/
│   ├── site/serenity/            # Reportes HTML de Serenity
│   └── cucumber-reports/         # Reportes de Cucumber
└── .github/workflows/            # CI/CD con GitHub Actions
```

## 🎯 Comandos Principales

### Compilar el proyecto
```bash
./gradlew build
```

### Ejecutar tests
```bash
# Ejecutar todos los tests (JUnit + Cucumber)
./gradlew test

# Ejecutar solo tests de Cucumber
./gradlew test --tests "CucumberTestSuite"

# Ejecutar tests de integración
./gradlew integrationTest

# Ejecutar un feature específico por tags
./gradlew test -Dcucumber.filter.tags="@regression"

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
- **BDD con Cucumber**: Escenarios escritos en Gherkin para colaboración con no técnicos
- **Modelos con Jackson**: Deserialización automática con `@JsonProperty`
- **Separation of Concerns**: Tasks, Questions, Models y Step Definitions separados
- **Type Safety**: Uso de genéricos y tipos específicos
- **CI/CD**: GitHub Actions con ejecución automática de tests
- **Living Documentation**: Reportes HTML detallados con Serenity
- **Configuración flexible**: Soporte para múltiples entornos (dev/prod)

### 🎯 API Utilizada
El proyecto utiliza la API pública [ReqRes](https://reqres.in/) para demostración:
- **GET** `/users?page={page}` - Listar usuarios
- **POST** `/register` - Registrar usuario

## 🥒 Cucumber BDD

El proyecto está configurado para ejecutar tests con **Cucumber** usando el formato **Gherkin** para escribir escenarios de prueba en lenguaje natural.

### Estructura de Cucumber

#### 📄 Features (Características)
Los archivos `.feature` se encuentran en `src/test/resources/features/` y describen el comportamiento esperado:

```gherkin
Feature: Register User
    Con el fin de poder administrar mis productos bancarios
    yo como usuario quiero poder registrarme 
    para poder utilizar pagos y ejecutar operaciones sobre mis productos

Scenario: Registro de usuario exitoso
    Given Fabian es un cliente que quiere poder administar sus productos
    When el envia la informacion requerida para el registro
    Then el deberia obtener una cuenta virtual para poder ingresar cuando lo requiera
```

#### 🔧 Step Definitions (Definiciones de Pasos)
Los steps se implementan en `src/main/java/com/example/stepdefinitios/`:

```java
@Given("Fabian es un cliente que quiere poder administar sus productos")
public void fabianEsUnClienteQueQuierePoderAdministarSusProductos() {
    String baseUrl = System.getProperty("restapi.baseurl", "https://reqres.in/api");
    fabian = Actor.named("Fabian")
        .whoCan(CallAnApi.at(baseUrl));
}

@When("el envia la informacion requerida para el registro")
public void elEnvíaLaInformacionRequeridaParaElRegistro() {
    RegisterUserInfo userInfo = new RegisterUserInfo();
    userInfo.setEmail("eve.holt@reqres.in");
    userInfo.setPassword("pistol");
    
    fabian.attemptsTo(RegisterUser.withInfo(userInfo));
}

@Then("el deberia obtener una cuenta virtual para poder ingresar cuando lo requiera")
public void elDeberiaObtenerUnaCuentaVirtualParaPoderIngresarCuandoLoRequiera() {
    fabian.should(seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200)));
}
```

#### 🏃 Runner de Cucumber
El runner `CucumberTestSuite.java` configura la ejecución de Cucumber:

```java
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.stepdefinitios")
public class CucumberTestSuite {
}
```

### Ejecutar Tests con Cucumber

```bash
# Ejecutar todos los tests de Cucumber
./gradlew test --tests "CucumberTestSuite"

# Ejecutar con tags específicos
./gradlew test -Dcucumber.filter.tags="@smoke"
./gradlew test -Dcucumber.filter.tags="@regression"
./gradlew test -Dcucumber.filter.tags="not @wip"

# Ver reportes de Cucumber
open target/cucumber-reports/cucumber.html
```

### Tags en Cucumber

Puedes agregar tags a tus escenarios en los archivos `.feature`:

```gherkin
@smoke @regression
Scenario: Registro de usuario exitoso
    Given Fabian es un cliente que quiere poder administar sus productos
    ...
```

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

#### 🥒 Step Definitions (Cucumber)
Implementaciones de los pasos de Gherkin que conectan los escenarios con el código:
- `RegisterUserSteps` - Steps para el proceso de registro de usuarios
- Utilizan el Screenplay Pattern con Actors, Tasks y Questions

#### 🔄 Interactions (Interacciones)
Interacciones personalizadas de bajo nivel con APIs:
- `Post` - Interacción personalizada para peticiones POST con logging detallado

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
