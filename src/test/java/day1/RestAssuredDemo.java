package day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class RestAssuredDemo {

    public static void main(String[] args) {

        //Base URI -> https://example.com
        RestAssured.baseURI = "https://demoqa.com";

        //Base path -> /example/path
        // when you mention base path you don't have to mention it within method(post, put, get, delete etc.)
        // RestAssured.basePath="/BookStore/v1/Books";

//        given - all input details
//        when - Submit the API -resource,http method
//        then - validate the response
        //2a85b2f0-8da4-4f82-a342-20932d2ec1d3
        /**
         * POST - create a user
         * */
//        RestAssured
//                .given().header("Content-Type", "application/json")
//                .body("{\n" +
//                        "  \"userName\": \"ElmTestUser10\",\n" +
//                        "  \"password\": \"Test123!\"\n" +
//                        "}")
//                .when().post("/Account/v1/User")
//                .then().assertThat().statusCode(201).log().all();


        /**
         * POST - add book for user
         * 2a85b2f0-8da4-4f82-a342-20932d2ec1d3
         * */
        String postBookResponse =
        given().log().all().auth().preemptive().basic("ElmTestUser10", "Test123!")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"userId\": \"2a85b2f0-8da4-4f82-a342-20932d2ec1d3\",\n" +
                        "  \"collectionOfIsbns\": [\n" +
                        "    {\n" +
                        "      \"isbn\": \"9781449365035\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when().log().all().post("/BookStore/v1/Books")
                .then().log().all().statusCode(201).extract().asString();

        System.out.println(postBookResponse);


    }

}
