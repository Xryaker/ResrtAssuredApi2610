import config.MySpecificationForRestAssured;
import data.character.episodes.Result;
import data.character.episodes.Root;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.when;

public class TestsEpisodes {
    @BeforeClass
    public static void b() {
        //  MySpecificationForRestAssured.create("");
    }

    @Test
    public void test() {
        Root root = when().get("https://rickandmortyapi.com/api/episode").then().extract().as(Root.class);
        do {
            for (Result l : root.results) {
                System.out.println(l.name);
            }
            if (root.info.next != null) {
                root = when().get(root.info.next).then().extract().as(Root.class);
            } else {
               break;
            }
            System.out.println("=======================================================================================");
        } while (true);
    }
}
