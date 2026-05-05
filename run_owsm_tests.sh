#!/bin/bash

ENV=${1:-local}
BROWSER=${2:-chrome}

sbt clean -Dbrowser=$BROWSER -Dbrowser.usePreviousVersion=true -Denvironment=$ENV -Dbrowser.option.headless=true "testOnly uk.gov.hmrc.ui.specs.* -- -n OwsmTests" testReport