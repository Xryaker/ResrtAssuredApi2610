import config.MySpecificationForRestAssured;
import data.character.Character;
import data.character.Result;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class TestRickAndMortyTEST {
    static String urlBase = "https://rickandmortyapi.com/api/";
    String urlCHAR = "character";

    @BeforeClass
    public static void b() {// set base config with allure filter
        MySpecificationForRestAssured.create(urlBase);
    }

    @Test
    public void test1() {//print all response
        String str = when()
                .get(urlCHAR)
                .then()
                .log()
                .all().toString();
        System.out.println(str);
    }

    @Test
    public void test2() {// info.count equals to int (826)
        when().get(urlCHAR).then().body("info.count", equalTo(826));
    }

    @Test
    public void test3() {//extrat field with path(info.count)
        int i = when().get(urlCHAR)
                .then().extract().body().path("info.count");
        System.out.println(i);
    }

    @Test
    public void test4() {//check all fields name not null
        when().get(urlCHAR).then().body("results.name", notNullValue());
    }

    @Test
    public void test5() { // get list of string
        List<String> listNames = when().get(urlCHAR).then().extract().jsonPath().getList("results.name", String.class);
        System.out.println(listNames);
    }

    @Test
    public void test6() {// get end pars all results to list<resul class>
        List<Result> resultList = when().get(urlCHAR).then().extract().jsonPath().getList("results", Result.class);
        for (Result l : resultList) {
            System.out.println(l.episode);
        }
    }

    @Test
    public void test() {// parse main object
        Character character = given().when().get(urlCHAR).then().extract().body().as(Character.class);
        System.out.println(character.info.count);
    }
}
//https://www.guru99.com/rest-assured.html