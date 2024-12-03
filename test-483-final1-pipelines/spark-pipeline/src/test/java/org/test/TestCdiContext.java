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
import java.util.Collections;
import java.util.List;

import jakarta.enterprise.inject.spi.Extension;

import com.boozallen.aissemble.core.cdi.CdiContext;

import io.smallrye.reactive.messaging.memory.InMemoryConnector;


/**
 * Provides the set of CDI context values needed to run Cucumber tests.
 *
 * GENERATED STUB CODE - PLEASE ***DO*** MODIFY
 *
 * Originally generated from: templates/cucumber.test.cdi.context.java.vm 
 */
public class TestCdiContext implements CdiContext {

	@Override
	public List<Class<?>> getCdiClasses() {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add(InMemoryConnector.class);
		return classes;
	}

	@Override
	public List<Extension> getExtensions() {
		return Collections.emptyList();
	}
}
