package com.example.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUsers implements Task {
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource("/users?page=2")
                .with(requestSpecification -> 
                    requestSpecification
                        .contentType(ContentType.JSON)
                        .header("header1", "value1")
                )
        );
    }

    public static GetUsers fromThePage() {
        return instrumented(GetUsers.class);
    }
}
