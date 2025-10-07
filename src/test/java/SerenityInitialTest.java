import com.example.models.users.Datum;
import com.example.models.users.RegisterUserInfo;
import com.example.questions.GetUsersQuestion;
import com.example.questions.ResponseCode;
import com.example.tasks.GetUsers;
import com.example.tasks.RegisterUser;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class SerenityInitialTest {

    public static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void getUsersFromApi() {
        Actor fabian = Actor.named("Fabian")
            .whoCan(CallAnApi.at(BASE_URL));

        fabian.attemptsTo(
            GetUsers.fromPage(2)
        );

        fabian.should(seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200)));
   
        Datum user = new GetUsersQuestion().answeredBy(fabian).getData().stream()
            .filter(u -> u.getId() == 7)
            .findFirst()
            .orElse(null);

        fabian.should(
            seeThat("Usuario no es nulo", act -> user, notNullValue()));

        fabian.should(
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

        fabian.attemptsTo(
            RegisterUser.withInfo(userInfo)
        ); 

        fabian.should(seeThat("El codigo de respuesta", ResponseCode.was(), equalTo(200)));
    }



    
}
