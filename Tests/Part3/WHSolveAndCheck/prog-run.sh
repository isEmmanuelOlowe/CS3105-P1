#!/bin/bash

dataDir="$TESTDIR/../../Instances/WormHole/"

read filename < /dev/stdin

#filename=standard.1.txt

java BHMain SOLVEWORM $dataDir/$filename | head -1 | cut -f 2- -d" " | java BHMain CHECKWORM $dataDir/$filename | head -1

