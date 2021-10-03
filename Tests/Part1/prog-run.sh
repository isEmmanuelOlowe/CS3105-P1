#!/bin/bash

dataDir="$TESTDIR/../Instances/BlackHole/"
#dataDir="$TESTDIR/../Instances/old/"

read filename < /dev/stdin

#filename=standard.1.txt

java BHMain CHECK $dataDir/$filename | head -1

