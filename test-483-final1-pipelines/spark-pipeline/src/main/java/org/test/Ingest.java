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


import jakarta.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;


/**
 * Performs the business logic for Ingest.
 *
 * Because this class is {@link ApplicationScoped}, exactly one managed singleton instance will exist
 * in any deployment.
 *
 * GENERATED STUB CODE - PLEASE ***DO*** MODIFY
 *
 * Originally generated from: templates/data-delivery-spark/synchronous.processor.impl.java.vm
 */
@ApplicationScoped
public class Ingest extends IngestBase {

    private static final Logger logger = LoggerFactory.getLogger(Ingest.class);

    public Ingest(){
        super("synchronous",getDataActionDescriptiveLabel());
    }

    /**
    * Provides a descriptive label for the action that can be used for logging (e.g., provenance details).
    *
    * @return descriptive label
    */
    private static String getDataActionDescriptiveLabel(){
        // TODO: replace with descriptive label
        return"Ingest";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeStepImpl() {
        // TODO: Add your business logic here for this step!
        logger.error("Implement executeStepImpl(..) or remove this pipeline step!");

    }


}
