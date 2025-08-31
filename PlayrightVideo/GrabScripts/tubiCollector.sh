#!/bin/bash

fileName="$1"
egrep -o "tubitv.com/movies/[0-9]*/[^\".]*" $fileName | awk '{system("./toUrl.sh " $NF)}'
