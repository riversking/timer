#!/usr/bin/env bash

set -eo pipefail

modules=(oauth-server)

for module in "${modules[@]}"; do
    docker build -t "timer/${module}:latest" ${module}
done
