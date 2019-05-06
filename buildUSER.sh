#!/usr/bin/env bash

set -eo pipefail

modules=(user-server)

for module in "${modules[@]}"; do
    docker build -t "timer/${module}:latest" ${module}
done
