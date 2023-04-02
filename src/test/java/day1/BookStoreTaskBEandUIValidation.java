package day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BookStoreTaskBEandUIValidation {

    //End to End testing
    @Test
    public void test() {

        //Get all books through api call
        RestAssured.baseURI = "https://demoqa.com/";
        String getBooksResponse =
                RestAssured
                        .when().get("/BookStore/v1/Books")
                        .then().assertThat().statusCode(200)
                        .extract().asString();

        JsonPath jsonPath = new JsonPath(getBooksResponse);

        int count = jsonPath.get("books.size()");
        List<String> allBookNamesBE = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            String title = jsonPath.get("books[" + i + "].title");
            allBookNamesBE.add(title);

        }

        System.out.println(allBookNamesBE);

        //go UI and get all books
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/books");

        List<WebElement> bookTitleElements = driver.findElements(By.xpath("//div[@class='action-buttons']"));
        List<String> allBookNamesUI = new ArrayList<>();
        for (WebElement title : bookTitleElements) {

            allBookNamesUI.add(title.getText());
        }
        System.out.println(allBookNamesUI);

        //Verify both (UI/BE) have same data
//        Assertions.assertEquals(allBookNamesBE, allBookNamesUI);
        Assert.assertEquals(allBookNamesBE,allBookNamesUI);


    }

}
