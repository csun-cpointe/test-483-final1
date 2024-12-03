package org.test.cdi;

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

import java.util.List;

import jakarta.enterprise.inject.spi.Extension;

import com.boozallen.aissemble.core.metadata.producer.MetadataProducer;

/**
 * Configures the CDI context for this application.
 *
 * Please **DO** modify with your customizations, as appropriate.
 *
 * Originally generated from: templates/pipeline.cdi.context.impl.java.vm 
 */
public class PipelinesCdiContext extends PipelinesCdiContextBase {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Class<?>> getCdiClasses() {
		List<Class<?>> customBeans = super.getCdiClasses();
		
		// Add any custom CDI classes here
		customBeans.add(MetadataProducer.class);

		return customBeans;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Extension> getExtensions() {
		List<Extension> extensions = super.getExtensions();

		// Add any custom extensions to Weld here

		return extensions;
	}

}
