#!/bin/bash

echo "Cleaning..."
./mvnw clean

echo "Packaging..."
./mvnw package || exit 1

echo "Running..."
java -jar target/b126-csp2-group2-1.0-SNAPSHOT-jar-with-dependencies.jar