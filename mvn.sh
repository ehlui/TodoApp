#!/bin/bash
#===========================================================================+
# Purpose: to build the project structure from jee-simple artifact directly |
# Requirements: To have the artifact in your local maven repo.              |
#               (it is not yet added to the maven-repository online)        |
# Artifact url: https://github.com/ehlui/mvn-archetype-jee7-docker          |
#===========================================================================+

echo "Using jee-simple artifact...."
echo "Enter your groupId: "
read -r groupId
echo
echo "Enter your artifactId: "
read -r artifactId

mvn archetype:generate \
  -DgroupId="$groupId"\
  -DartifactId="$artifactId"\
  -DarchetypeGroupId=com.ehlui\
  -DarchetypeArtifactId=jee-simple\
  -DarchetypeVersion=1.0 -DinteractiveMode=false