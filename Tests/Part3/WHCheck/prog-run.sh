#!/bin/bash

dataDir="$TESTDIR/../../Instances/WormHole/"

read filename < /dev/stdin

#filename=standard.1.txt

java BHMain CHECKWORM $dataDir/$filename | head -1

