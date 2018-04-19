package eohmc.selenium.core.testCases.API;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class APITesting
{
    //It will Check status code
    @Test
    public void testConnectRequest()
    {
        given().
                get("http://localhost:3000/posts/1").
                then().
                statusCode(200);
    }

    //It will parameters and headers can be set
    @Test
    public void testHasItemFunction()
    {
        given().
                param("key1","value").
                header("headerA","valueA").
                when().
                get("http://services.groupkt.com/country/get/iso2code/gb").
                then().
                statusCode(200).
                log().all();
    }
}
