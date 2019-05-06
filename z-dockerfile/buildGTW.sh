#!/usr/bin/env bash

set -eo pipefail

modules=(gateway)

for module in "${modules[@]}"; do
    docker build -t "timer/${module}:latest" ${module}
done
