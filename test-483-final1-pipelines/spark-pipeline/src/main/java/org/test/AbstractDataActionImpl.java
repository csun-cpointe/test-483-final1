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

import org.aeonbits.owner.KrauseningConfigFactory;
import com.boozallen.aiops.data.delivery.spark.AbstractDataAction;
import com.boozallen.aiops.data.delivery.spark.SparkConfig;

 /**
 * Contains the general concepts needed to perform a base AIOps Reference Architecture Data Action. A Data Action is a
 * step within a Data Flow.
  *
 * GENERATED STUB CODE - PLEASE ***DO*** MODIFY
  *
  * Generated from: templates/data-delivery-spark/abstract.data.action.impl.java.vm
  */
public abstract class AbstractDataActionImpl extends AbstractDataAction {

    protected static final SparkConfig config = KrauseningConfigFactory.create(SparkConfig.class);

    protected AbstractDataActionImpl(String subject, String action) {
        super(subject, action);
    }
}
