#!/bin/bash

#=======================================================+
# Purpose: Deploy a docker-compose using .env configs   |
# Requirements: To have docker installed                |
#=======================================================+

# 1. Removing all containers from the appTask related
docker rm -f $(docker ps -aq -f name=todoapp)

# 2. Variables defined in `.env` will be exported
# into this script's environment:
set -a
source .env

# 3. Let's populate the variables in our compose-file template
# (reading the .env exported through docker-compose) then deploy it!
< docker-compose.yml envsubst | docker-compose -f - -p todoapp up -d

# --------------------------------------------------------------------------------
# Docker compose docs:
#  - Use a -f with - (dash) as the filename to read the configuration from stdin.