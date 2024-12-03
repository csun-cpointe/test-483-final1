# Script for creating base Spark Worker Docker image
#
# GENERATED DockerFile - please ***DO*** modify.
#
# Generated from: templates/general-docker/spark-worker.docker.file.vm

ARG DOCKER_BASELINE_REPO_ID
ARG VERSION_AISSEMBLE

FROM ${DOCKER_BASELINE_REPO_ID}boozallen/aissemble-spark:${VERSION_AISSEMBLE}

USER root


WORKDIR /

#Install 3rd party Pyspark pipeline dependencies (does nothing if you only have Spark pipelines)
COPY ./target/dockerbuild/requirements/. /tmp/requirements/
RUN set -e && files=$(find /tmp/requirements -path '/tmp/requirements/*/*' -name requirements.txt -type f); for file in $files; do python3 -m pip install --no-cache-dir -r $file || exit 1; done;

#Install monorepo Pyspark pipeline dependencies (does nothing if you only have Spark pipelines)
COPY ./target/dockerbuild/wheels/. /tmp/wheels/
RUN set -e && files=$(find /tmp/wheels -path '/tmp/wheels/*' -name '*.whl' -type f); for file in $files; do python3 -m pip install --no-cache-dir $file || exit 1; done;

#Pipelines
COPY --chown=spark:spark --chmod=777 ./target/dockerbuild/. /opt/spark/jobs/pipelines/

#Install Pyspark pipelines (does nothing if you only have Spark pipelines)
RUN set -e && files=$(find /opt/spark/jobs -path '/opt/spark/jobs/pipelines/*/*' -name '*.tar.gz' -type f); for file in $files; do python3 -m pip install --no-deps --no-cache-dir $file || exit 1; done;

COPY --chown=spark ./src/main/resources/krausening/ ${SPARK_HOME}/krausening/

# Switch to the spark user (which *is* 1001)
USER spark
WORKDIR /opt/spark/work-dir/
