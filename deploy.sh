#!/bin/sh
APP_NAME=test-483-final1

is_app_running() {
  argocd app get $APP_NAME --server localhost:30080 --plaintext > /dev/null 2>&1
}

deploy() {

  if is_app_running; then
    echo "test-483-final1 is deployed"
  else
    branch=$(git rev-parse --abbrev-ref HEAD)
    echo "Deploying test-483-final1 from $branch..."
    argocd app create $APP_NAME \
      --server localhost:30080 --plaintext \
      --dest-namespace test-483-final1 \
      --dest-server https://kubernetes.default.svc \
      --repo https://github.com/csun-cpointe/test-483-final1.git \
      --path test-483-final1-deploy/src/main/resources \
      --revision $branch \
      --helm-set spec.targetRevision=$branch \
      --values values.yaml \
      --values values-dev.yaml \
      --sync-policy automated \
      --insecure
  fi
}

down() {
  if is_app_running; then
    echo "Tearing down app..."
    argocd app delete $APP_NAME --server localhost:30080 --plaintext --yes
  else
    echo "test-483-final1 is not deployed"
  fi
}

print_usage() {
  echo "Usage: $0 [up|down]"
  echo "   up          deploy application (automatically starts deployment infrastructure if needed)"
  echo "   down        tear down application"
}

if [ "$1" = "up" ]; then
  deploy
elif [ "$1" = "down" ]; then
  down
else
  print_usage
fi
