#!/bin/sh

if [ "$1" == "up" ]; then
  argocd app get test-483-final --server localhost:30080 --plaintext &> /dev/null

  if [ $? -eq 0 ]; then
    echo "test-483-final is deployed"
  else
    argocd app create test-483-final \
      --server localhost:30080 --plaintext \
      --dest-namespace test-483-final \
      --dest-server https://kubernetes.default.svc \
      --repo https://github.com/csun-cpointe/test-483-final.git \
      --path test-483-final-deploy/src/main/resources \
      --revision main \
      --values values.yaml \
      --values values-dev.yaml \
      --sync-policy automated
  fi
else
  if [ "$1" == "down" ]; then
    argocd app delete test-483-final --server localhost:30080 --plaintext
  else
    echo "error: you must use \"up\" or \"down\" command for \"deploy\""
  fi
fi

