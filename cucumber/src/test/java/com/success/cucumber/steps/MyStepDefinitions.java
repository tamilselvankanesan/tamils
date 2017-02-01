package com.success.cucumber.steps;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class MyStepDefinitions {

	@Given("^sample feature file is ready$")
	public void sample_feature_file_is_ready() {
	    // Express the Regexp above with the code you wish you had
		System.out.println("Hello world");
	}

	@When("^I run the feature file$")
	public void I_run_the_feature_file() {
	    // Express the Regexp above with the code you wish you had
		System.out.println("Hello world");
	}

	@Then("^run should be successful$")
	public void run_should_be_successful() {
	    // Express the Regexp above with the code you wish you had
		System.out.println("Hello world");
	}
}
