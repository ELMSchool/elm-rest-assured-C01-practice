package day1;

import io.restassured.path.json.JsonPath;

public class JsonPathPractice {


    public static void main(String[] args) {

        String jsonStr = "{\n" +
                "\t\"users\": \n" +
                "             [\n" +
                "                {\n" +
                "                \"userName\": \"User1\",\n" +
                "                \"password\": \"Password1\",\n" +
                "                 \"special\" : [{\"specialKey\":175}]\n" +
                "                },\n" +
                "                {\n" +
                "                \"userName\": \"User2\",\n" +
                "                \"password\": \"Password2\"\n" +
                "                },\n" +
                "\n" +
                "                {\n" +
                "                \"userName\": \"User3\",\n" +
                "                \"password\": \"Password3\"\n" +
                "                }\n" +
                "             ] \n" +
                " }";


        /*
          * {
          *  users:
          *  [
                {"userName": "User",
                "password": "Password"

                },
                 {"userName": "User",
                "password": "Password"
                },
                 {"userName": "User",
                "password": "Password"
                }
             ]
             }
        * */
        JsonPath jsonPath = new JsonPath(jsonStr);
//        System.out.println(jsonPath.get("users[0].special[0].specialKey").toString());

        int key = jsonPath.get("users[0].special[0].specialKey");
        System.out.println(key+5);
    }

}
