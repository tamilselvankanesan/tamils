package stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java8.En;
import pages.HelloWorldPage;

public class HelloWordStepDef implements En{
  
  private HelloWorldPage page = new HelloWorldPage();
  
  @Before
  public void before(){
  }
  
  public HelloWordStepDef(){
    Given("I want to write a step with precondition", () -> {
      // Write code here that turns the phrase above into concrete actions
      page.openUrl();
  });

  Given("some other precondition", () -> {
      // Write code here that turns the phrase above into concrete actions
  });

  When("I complete action", () -> {
      // Write code here that turns the phrase above into concrete actions
  });

  When("some other action", () -> {
      // Write code here that turns the phrase above into concrete actions
  });

  When("yet another action", () -> {
      // Write code here that turns the phrase above into concrete actions
  });

  Then("I validate the outcomes", () -> {
      // Write code here that turns the phrase above into concrete actions
  });

  Then("check more outcomes", () -> {
      // Write code here that turns the phrase above into concrete actions
  });
  }
}
