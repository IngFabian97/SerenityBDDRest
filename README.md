# Serenity BDD REST Automation Framework

Proyecto de automatización de pruebas REST API usando **Serenity BDD**, **REST Assured**, **JUnit 5** y **Gradle**.

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
├── build.gradle.kts          # Configuración de Gradle (Kotlin DSL)
├── settings.gradle.kts        # Configuración del proyecto
├── gradle.properties          # Propiedades y perfiles
├── gradlew                    # Wrapper de Gradle para Unix/Mac
├── gradlew.bat                # Wrapper de Gradle para Windows
├── gradle/                    # Archivos del Gradle Wrapper
├── src/
│   ├── main/java/            # Código fuente
│   └── test/java/            # Tests
└── target/site/serenity/     # Reportes generados
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

## 📝 Ejemplo de Test

```java
@ExtendWith(SerenityJUnit5Extension.class)
public class SerenityInitialTest {

    public static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void getUsers() {
        Actor fabian = Actor.named("Fabian")
            .whoCan(CallAnApi.at(BASE_URL));

        fabian.attemptsTo(
            Get.resource("/users?page=2")
        );

        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
    }
}
```

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
