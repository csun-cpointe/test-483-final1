allow_k8s_contexts('local')
docker_prune_settings(num_builds=1, keep_recent=1)

aissemble_version = '1.11.0-SNAPSHOT'

build_args = { 'DOCKER_BASELINE_REPO_ID': 'ghcr.io/',
               'VERSION_AISSEMBLE': aissemble_version}

# Kafka
yaml = helm(
    'test-483-final1-deploy/src/main/resources/apps/kafka-cluster',
    values=['test-483-final1-deploy/src/main/resources/apps/kafka-cluster/values.yaml',
        'test-483-final1-deploy/src/main/resources/apps/kafka-cluster/values-dev.yaml']
)
k8s_yaml(yaml)

k8s_kind('SparkApplication', image_json_path='{.spec.image}')

# spark-worker-image
docker_build(
    ref='test-483-final1-spark-worker-docker',
    context='test-483-final1-docker/test-483-final1-spark-worker-docker',
    build_args=build_args,
    extra_tag='test-483-final1-spark-worker-docker:latest',
    dockerfile='test-483-final1-docker/test-483-final1-spark-worker-docker/src/main/resources/docker/Dockerfile'
)



yaml = local('helm template oci://ghcr.io/boozallen/aissemble-spark-application-chart --version %s --values test-483-final1-pipelines/spark-pipeline/src/main/resources/apps/spark-pipeline-base-values.yaml,test-483-final1-pipelines/spark-pipeline/src/main/resources/apps/spark-pipeline-dev-values.yaml' % aissemble_version)
k8s_yaml(yaml)
k8s_resource('spark-pipeline', port_forwards=[port_forward(4747, 4747, 'debug')], auto_init=False, trigger_mode=TRIGGER_MODE_MANUAL)
# policy-decision-point
docker_build(
    ref='test-483-final1-policy-decision-point-docker',
    context='test-483-final1-docker/test-483-final1-policy-decision-point-docker',
    build_args=build_args,
    dockerfile='test-483-final1-docker/test-483-final1-policy-decision-point-docker/src/main/resources/docker/Dockerfile'
)
# pyspark-pipeline-compiler
local_resource(
    name='compile-pyspark-pipeline',
    cmd='cd test-483-final1-pipelines/pyspark-pipeline && poetry run behave tests/features && poetry build && cd - && \
    cp -r test-483-final1-pipelines/pyspark-pipeline/dist/* test-483-final1-docker/test-483-final1-spark-worker-docker/target/dockerbuild/pyspark-pipeline && \
    cp test-483-final1-pipelines/pyspark-pipeline/dist/requirements.txt test-483-final1-docker/test-483-final1-spark-worker-docker/target/dockerbuild/requirements/pyspark-pipeline',
    deps=['test-483-final1-pipelines/pyspark-pipeline'],
    auto_init=False,
    ignore=['**/dist/']
)

yaml = helm(
   'test-483-final1-deploy/src/main/resources/apps/spark-operator',
   name='spark-operator',
   values=['test-483-final1-deploy/src/main/resources/apps/spark-operator/values.yaml',
       'test-483-final1-deploy/src/main/resources/apps/spark-operator/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-483-final1-deploy/src/main/resources/apps/spark-infrastructure',
   name='spark-infrastructure',
   values=['test-483-final1-deploy/src/main/resources/apps/spark-infrastructure/values.yaml',
       'test-483-final1-deploy/src/main/resources/apps/spark-infrastructure/values-dev.yaml']
)
k8s_yaml(yaml)
k8s_yaml('test-483-final1-deploy/src/main/resources/apps/spark-worker-image/spark-worker-image.yaml')



yaml = local('helm template oci://ghcr.io/boozallen/aissemble-spark-application-chart --version %s --values test-483-final1-pipelines/pyspark-pipeline/src/pyspark_pipeline/resources/apps/pyspark-pipeline-base-values.yaml,test-483-final1-pipelines/pyspark-pipeline/src/pyspark_pipeline/resources/apps/pyspark-pipeline-dev-values.yaml' % aissemble_version)
k8s_yaml(yaml)
k8s_resource('pyspark-pipeline', port_forwards=[port_forward(4747, 4747, 'debug')], auto_init=False, trigger_mode=TRIGGER_MODE_MANUAL)
yaml = helm(
   'test-483-final1-deploy/src/main/resources/apps/policy-decision-point',
   name='policy-decision-point',
   values=['test-483-final1-deploy/src/main/resources/apps/policy-decision-point/values.yaml',
       'test-483-final1-deploy/src/main/resources/apps/policy-decision-point/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-483-final1-deploy/src/main/resources/apps/pipeline-invocation-service',
   name='pipeline-invocation-service',
   values=['test-483-final1-deploy/src/main/resources/apps/pipeline-invocation-service/values.yaml',
       'test-483-final1-deploy/src/main/resources/apps/pipeline-invocation-service/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-483-final1-deploy/src/main/resources/apps/s3-local',
   name='s3-local',
   values=['test-483-final1-deploy/src/main/resources/apps/s3-local/values.yaml',
       'test-483-final1-deploy/src/main/resources/apps/s3-local/values-dev.yaml']
)
k8s_yaml(yaml)
# Add deployment resources here