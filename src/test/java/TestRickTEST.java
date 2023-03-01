import config.MySpecificationForRestAssured;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.when;

public class TestRickTEST {
    String url="character";
    @BeforeClass
    public static void b(){
        MySpecificationForRestAssured.create("https://rickandmortyapi.com/api/");
    }
    @Test
    @Description(" List<String> listUrl=when()\n" +
            "                .get(url)\n" +
            "                .then()\n" +
            "                .log().all()\n" +
            "                .extract()\n" +
            "                .jsonPath()\n" +
            "                .getList(\"results.location.url\",String.class);")
    @DisplayName("GET all url locations")
    public void test1(){ //  get list urls
        List<String> listUrl=when()
                .get(url)
                .then()
                .log().all()
                .extract()
                .jsonPath()
                .getList("results.location.url",String.class);
        for(String l:listUrl){
            System.out.println(l);
        }
    }

}
