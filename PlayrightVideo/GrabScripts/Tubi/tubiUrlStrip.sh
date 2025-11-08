#!/bin/bash
fileName=$1
egrep -o "href=\"/category/[/a-z_^\"]+" $fileName | sed 's/href=//g' | awk '{system(" echo https://tubitv.com" $NF)}'
