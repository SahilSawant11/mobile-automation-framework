#!/usr/bin/env bash
# Simple runner script
mvn clean test -Denv=${1:-dev}
