import com.example.tasks.GetUsers;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
    }

    
}
