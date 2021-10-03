#!/bin/bash

dataDir="$TESTDIR/../../Instances/BlackHole/"

read filename < /dev/stdin

# just look at first field of first line 
java BHMain SOLVE $dataDir/$filename | head -1 | cut -f 1 -d" "



