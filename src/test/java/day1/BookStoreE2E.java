package day1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.Matchers.*;



public class BookStoreE2E {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://demoqa.com";
        //1. Create a user
        String postUserResponse =
        RestAssured
                .given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "  \"userName\": \"ElmUser15\",\n" +
                        "  \"password\": \"Test123!\"\n" +
                        "}")
                .when().post("/Account/v1/User")
                .then().assertThat().statusCode(201).body("username", equalTo("ElmUser15"))
                .log().all().extract().asString();
        JsonPath js = new JsonPath(postUserResponse);
        String userId = js.get("userID");

        //2. Add book for the user
        String isbn = "9781449325862";
        String postABook =
        RestAssured
                .given().log().all().header("Content-Type","application/json")
                .auth().preemptive().basic("ElmUser15","Test123!")
                .body("{\n" +
                        "  \"userId\": \""+userId+"\",\n" +
                        "  \"collectionOfIsbns\": [\n" +
                        "    {\n" +
                        "      \"isbn\": \""+isbn+"\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when().post("/BookStore/v1/Books")
                .then().assertThat().statusCode(201)
                .log().all().extract().asString();


        //3. Get User Information validate book is there
        String getUserInfoResponse=
        RestAssured
                .given().log().all().header("Content-Type","application/json")
                .auth().preemptive().basic("ElmUser15","Test123!")
                .get("https://demoqa.com/Account/v1/User/"+userId)
                .then().log().all().assertThat().statusCode(200).extract().asString();
        JsonPath userInfoJP = new JsonPath(getUserInfoResponse);
        String bookTitle = userInfoJP.get("books[0].title");
        Assertions.assertEquals(bookTitle, "Git Pocket Guide");

        //4. Delete User
        RestAssured
                .given().log().all().header("Content-Type","application/json")
                .auth().preemptive().basic("ElmUser15","Test123!")
                .delete("https://demoqa.com/Account/v1/User/"+userId)
                .then().assertThat().statusCode(204);
    }


}
