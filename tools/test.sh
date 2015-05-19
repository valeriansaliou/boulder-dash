#!/bin/sh

##
#  Boulder Dash
#  Test script
#
#  Authors: Valérian Saliou, Colin Leverger
##

# Go to updir
ABSPATH=$(cd "$(dirname "$0")"; pwd)
BASE_DIR="$ABSPATH/../"

# Fixes issues w/ GruntJS
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8

# Proceed deployment
echo "Testing..."

cd "$BASE_DIR"

ls #TODO: test command
rc=$?

# Check for errors
if [ $rc = 0 ]; then
  echo "Done."
else
  echo "Error."
fi

exit $rc
