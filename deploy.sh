#!/bin/bash

set -e

#VERSION=$(date +'%Y-%m-%dt%H-%M')
VERSION=latest
IMAGE=us-central1-docker.pkg.dev/english-with/exercise-expert-native/exercise-expert-native:$VERSION

echo "Version: ${VERSION}"
echo "Image: ${IMAGE}"

#mvn spring-boot:build-image

docker tag docker.io/library/exercise-expert:0.0.1-SNAPSHOT $IMAGE
docker push $IMAGE

gcloud beta run services replace service.yaml
