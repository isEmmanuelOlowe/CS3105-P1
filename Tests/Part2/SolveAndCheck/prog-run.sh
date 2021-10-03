#!/bin/bash

dataDir="$TESTDIR/../../Instances/BlackHole/"


read filename < /dev/stdin

# just look at every field except first of first line to get solution then pass it in 
java BHMain SOLVE $dataDir/$filename | head -1 | cut -f 2- -d" " | java BHMain CHECK $dataDir/$filename | head -1




