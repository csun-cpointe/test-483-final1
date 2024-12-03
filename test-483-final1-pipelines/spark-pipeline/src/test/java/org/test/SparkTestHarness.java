package org.test;

/*-
 * #%L
 * Verify 1.11.0-SNAPSHOT Release::Pipelines::Spark Pipeline
 * %%
 * Copyright (C) 2021 Booz Allen
 * %%
 * All Rights Reserved. You may not copy, reproduce, distribute, publish, display,
 * execute, modify, create derivative works of, transmit, sell or offer for resale,
 * or in any way exploit any part of this solution without Booz Allen Hamilton's
 * express written permission.
 * #L%
 */

import java.util.ArrayList;
import java.util.List;

import org.aeonbits.owner.KrauseningConfigFactory;
import org.apache.log4j.Level;
import org.apache.spark.sql.SparkSession;

import com.boozallen.aissemble.core.cdi.CdiContext;
import com.boozallen.aiops.data.delivery.spark.SparkConfig;

import io.smallrye.reactive.messaging.memory.InMemoryConnector;

/**
 * Sets up Spark to run within Cucumber.
 *
 * GENERATED STUB CODE - PLEASE ***DO*** MODIFY
 *
 * Originally generated from: templates/cucumber.spark.test.impl.harness.java.vm 
 */
public class SparkTestHarness extends SparkTestBaseHarness {

	/**
	 * {@inheritDoc}
	 */
	protected void setLogging() {
		// suppress excessive logging from spark and smallrye
		org.apache.log4j.Logger.getLogger("org").setLevel(Level.WARN);
		org.apache.log4j.Logger.getLogger("io").setLevel(Level.ERROR);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected void configureMessagingChannels() {
		// set up smallrye channels to use in-memory connector so we don't
		// need to bring up kafka for the tests:
		
		InMemoryConnector.switchIncomingChannelsToInMemory("request-channel");
		InMemoryConnector.switchOutgoingChannelsToInMemory("response-channel");
		InMemoryConnector.switchOutgoingChannelsToInMemory("metadata-ingest");
	}
	
    /**
	 * {@inheritDoc}
	 */
	protected List<CdiContext> getCdiContexts() {
		List<CdiContext> testContexts = new ArrayList<>();
		testContexts.add(new TestCdiContext());
		return testContexts;
	}
	
}
