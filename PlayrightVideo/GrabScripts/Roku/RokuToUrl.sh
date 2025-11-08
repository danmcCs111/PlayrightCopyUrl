#!/bin/bash
url=$1
fileName="../../GrabFolder/Roku/"`echo $url | egrep -o "egrep -o "[a-zA-Z\-]*$"`.url
echo $fileName
echo [InternetShortcut] > $fileName
echo "URL="$url >> $fileName
