package com.example.tasks;

import com.example.models.users.RegisterUserInfo;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class RegisterUser implements Task {

    private Object userInfo;

    public RegisterUser(Object userInfo) {
        this.userInfo = userInfo;
    }

    public static Performable withInfo(Object userInfo) {
        return new RegisterUser(userInfo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Post.to("/register")
                .with(requestSpecification -> requestSpecification
                    .contentType(ContentType.JSON)
                    .header("x-api-key", "reqres-free-v1")
                    .body(userInfo)
                )
        );
    }
}
