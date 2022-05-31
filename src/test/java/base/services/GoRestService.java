package base.services;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This Class contains all methods/actions needed by functions related to Subscription Service
 */
public class GoRestService {

    /**
     * Constants definitions
     */
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String SUBSCRIPTION_CONTENT_TYPE = "application/json";
    private static final String AUTHS = "authTokens/tokens.json";

    /**
     * Local variables
     */
    private Response response;

    private JSONObject jsonFileToJsonObject(String file) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject dataObject = (JSONObject) obj;
            return dataObject;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to send a GET request based on an endpoint
     *
     * @param endpoint (String)
     */
    @Step("I get the endpoint {string}")
    public void sendGetRequest(String endpoint) {
        response = SerenityRest.given()
                .contentType(CONTENT_TYPE)
                .header(CONTENT_TYPE, SUBSCRIPTION_CONTENT_TYPE)
                .when()
                .get(endpoint);
    }

    /**
     * This method DELETE a user by id returning the Response to compare the status code
     *
     * @param id
     * @return Response object to assert/compare response code
     */
    @Step("I send a DELETE query by id {int}")
    public Response sendDeleteQueryById(int id) {
        response = SerenityRest.given()
                .contentType(CONTENT_TYPE)
                .headers("Authorization",jsonFileToJsonObject(AUTHS).get("token1"),CONTENT_TYPE, SUBSCRIPTION_CONTENT_TYPE)
                .when()
                .delete("https://gorest.co.in/public/v2/users/" + id);

        return response;
    }

}
