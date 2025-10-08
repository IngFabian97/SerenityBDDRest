package com.example.tasks;

import com.example.interactions.Post;
import com.example.models.users.RegisterUserInfo;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RegisterUser implements Task {

    private final RegisterUserInfo userInfo;

    public RegisterUser(RegisterUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static Performable withInfo(RegisterUserInfo userInfo) {
        return instrumented(RegisterUser.class, userInfo);
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
