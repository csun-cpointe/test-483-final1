from pyspark.sql import SparkSession
from pyspark_pipeline.generated import environment_base
from krausening.logging import LogManager

"""
Behave test environment setup to configure Spark for unit tests.

GENERATED STUB CODE - PLEASE ***DO*** MODIFY

Originally generated from: templates/data-delivery-pyspark/behave.environment.py.vm
"""
logger = LogManager.get_instance().get_logger("Environment")


def before_all(context):
    # Model-driven initialization.  Set any environment variables (ie KRAUSENING_BASE) prior to this call!
    environment_base.initialize()
    # Set test spark session for all tests
    context.test_spark_session = SparkSession.builder.getOrCreate()


def after_all(context):
    environment_base.cleanup()
    # Stop test spark session after all tests
    context.test_spark_session.stop()
