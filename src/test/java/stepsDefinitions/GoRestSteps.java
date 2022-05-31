package stepsDefinitions;

import base.services.GoRestService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class GoRestSteps {

    @Steps
    GoRestService goRestService;

    @Given("I get the response from the endpoint")
    public void iGetTheResponseFromTheEndpoint() {
        goRestService.sendGetRequest("https://gorest.co.in/public/v2/users");
    }

    @Given("I get the response from the endpoint with id {}")
    public void iGetTheResponseFromTheEndpointById(int userId) {
        goRestService.sendGetRequest("https://gorest.co.in/public/v2/users/" + userId);
    }

    @When("I DELETE a user by id {}")
    public void iDeleteUserByIdDataTable(int userId) {
        goRestService.sendDeleteQueryById(userId).getBody().prettyPrint();
    }

    @Then("I get the response code equals to {}")
    public void iGetTheResponseCodeEqualsTo(int responseCode) {
        restAssuredThat(response -> response.statusCode(responseCode));
    }

}
