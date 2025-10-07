# 🥒 Guía Rápida de Cucumber en el Proyecto

## 📁 Archivos Creados

### 1. Feature File
**Ubicación:** `src/test/resources/features/register_users.feature`
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

### 2. Step Definitions
**Ubicación:** `src/main/java/com/example/stepdefinitios/RegisterUserSteps.java`

Implementa los pasos del Gherkin usando el Screenplay Pattern.

### 3. Cucumber Runner
**Ubicación:** `src/test/java/com/example/runners/CucumberTestSuite.java`

Configura y ejecuta los tests de Cucumber con Serenity.

### 4. Configuración de Serenity
**Ubicación:** `serenity.properties`

Archivo de configuración para Serenity BDD y Cucumber.

## 🚀 Comandos Disponibles

```bash
# Ejecutar todos los tests (JUnit + Cucumber)
./gradlew clean test aggregate

# Ejecutar solo Cucumber
./gradlew test --tests "CucumberTestSuite"

# Ejecutar con tags específicos
./gradlew test -Dcucumber.filter.tags="@smoke"

# Ver reportes
open target/site/serenity/index.html
open target/cucumber-reports/cucumber.html
```

## 🏷️ Uso de Tags

Agrega tags a tus escenarios:

```gherkin
@smoke @regression
Scenario: Registro de usuario exitoso
    Given ...
```

Ejecuta por tags:
```bash
./gradlew test -Dcucumber.filter.tags="@smoke"
./gradlew test -Dcucumber.filter.tags="@regression"
./gradlew test -Dcucumber.filter.tags="not @wip"
```

## 📝 Crear Nuevos Scenarios

1. **Crea el Feature File** en `src/test/resources/features/`
2. **Implementa los Steps** en `src/main/java/com/example/stepdefinitios/`
3. **Ejecuta** con `./gradlew test`

## ✅ Verificación

Estado actual:
- ✅ Cucumber configurado y funcionando
- ✅ Test "Registro de usuario exitoso" ejecutándose
- ✅ Reportes de Serenity generándose correctamente
- ✅ Integración JUnit 5 + Cucumber Platform
- ✅ README actualizado con documentación completa

## 🔗 Enlaces Útiles

- [Cucumber Docs](https://cucumber.io/docs/cucumber/)
- [Serenity BDD with Cucumber](https://serenity-bdd.info/docs/guide/user_guide_intro)
- [Gherkin Reference](https://cucumber.io/docs/gherkin/reference/)
