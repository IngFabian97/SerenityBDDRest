package com.example.stepdefinitios;

import com.example.models.users.RegisterUserInfo;
import com.example.questions.ResponseCode;
import com.example.tasks.RegisterUser;

import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterUserSteps {

    Actor fabian;

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

        fabian.attemptsTo(
            RegisterUser.withInfo(userInfo)
        ); 
    }

    @Then("el deberia obtener una cuenta virtual para poder ingresar cuando lo requiera")
    public void elDeberiaObtenerUnaCuentaVirtualParaPoderIngresarCuandoLoRequiera() {
        fabian.should(seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200)));
    }
}

