import data.exchanges.Exchange;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.when;

public class TestExchange {
   static List<Exchange> exchangeList;
    @BeforeClass
    public static void b(){// parse massive exchages
        exchangeList=when()
                .get("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json")
                .then()
                .extract().body().jsonPath().getList(".", Exchange.class);
    }
    @Test
    public void test1(){
        for(Exchange l:exchangeList){
            System.out.println(l.txt);
        }
    }
}
