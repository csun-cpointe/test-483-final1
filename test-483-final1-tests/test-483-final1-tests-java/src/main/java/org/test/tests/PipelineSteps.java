package org.test.tests;

/*-
 * #%L
 * Verify 1.11.0-SNAPSHOT Release::Tests::Java
 * %%
 * Copyright (C) 2021 Booz Allen
 * %%
 * All Rights Reserved. You may not copy, reproduce, distribute, publish, display,
 * execute, modify, create derivative works of, transmit, sell or offer for resale,
 * or in any way exploit any part of this solution without Booz Allen Hamilton's
 * express written permission.
 * #L%
 */

import static org.junit.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PipelineSteps {

    private static final Logger logger = LoggerFactory.getLogger(PipelineSteps.class);

    @Before("@pipeline")
    public void setup() {
    }

    @After("@pipeline")
    public void cleanup() {
    }

    @Given("a precondition")
    public void a_precondition() {
        // code the items you need before performing your action
    }

    @When("an action occurs")
    public void an_action_occurs() {
        // execute your action
    }

    @Then("a postcondition results")
    public void a_postcondition_results() {
        // check for expected postconditions - continue to use normal assert pattern within tests
        assertTrue(true);
    }

}
