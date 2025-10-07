Feature: Register User
    Con el fin de poder administrar mis productos bancarios
    yo como usuario quiero poder registrarme 
    para poder utilizar pagos y ejecutar operaciones sobre mis productos

Scenario: Registro de usuario exitoso
    Given Fabian es un cliente que quiere poder administar sus productos
    When el envia la informacion requerida para el registro
    Then el deberia obtener una cuenta virtual para poder ingresar cuando lo requiera