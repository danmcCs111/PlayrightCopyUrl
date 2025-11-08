#!/bin/bash

fileName="$1"
egrep -io "\/browse\/[a-zA-Z\.0-9]*\/[a-zA-Z\-]*" $fileName | egrep -v "\/more|\/premium-subscriptions" | awk '{system("./RokuToUrl.sh " $NF)}'
