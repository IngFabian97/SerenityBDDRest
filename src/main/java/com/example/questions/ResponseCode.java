package com.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;

public class ResponseCode implements Question {
    
    public static Question<Integer> was() {
        return new ResponseCode();
    }

    @Override
    public Integer answeredBy(Actor actor) {
       return SerenityRest.lastResponse().statusCode();
    }

}
