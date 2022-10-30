import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Task3 {
    @Test
    public void check_status_code(){
        RestAssured.baseURI="https://bookstore.toolsqa.com/BookStore/v1/Books";
        //-Assert that status code is 200
        given().
                when().
                get(baseURI)
                .then().assertThat().statusCode(200);
    }

    @Test
    public void check_last_book_isbn_and_pages(){
        RestAssured.baseURI="https://bookstore.toolsqa.com/BookStore/v1/Books";
        List<Integer> list= Arrays.asList(234, 254);
        JsonPath json_path= given().
                when().get(baseURI).then().
                        log().body().extract().jsonPath();
        //-Assert last book's isbn equal to 9781593277574
        String a=json_path.getString("books.isbn[-1]");
        Assert.assertEquals(a, "9781593277574");
        //-Assert first and second book's pages count is equal to 234 and 254
        Assert.assertEquals(json_path.getList("books.pages[0,1]"), list);

    }


}
