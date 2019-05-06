#!/usr/bin/env bash

set -eo pipefail

modules=(file-server)

for module in "${modules[@]}"; do
    docker build -t "timer/${module}:latest" ${module}
done
