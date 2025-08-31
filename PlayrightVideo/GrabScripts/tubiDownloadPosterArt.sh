#!/bin/bash

fileName=$1
fileNameTmp=$2
mov=$3

url=https://`egrep -o URL=.* $mov | sed 's/URL=//g' | sed 's/^.*://g'`
movTmp=`echo $mov | sed "s/..\/GrabFolder\/Tubi\///g"`
fileNameTmp=$fileNameTmp$movTmp
movSave=$movTmp.png
echo $url $movSave
java -cp ../playright_copy_url.jar PlayrightVideo.PlayrightVideo.TubiSingleCollect npx playwright codegen demo.playwright.dev/todomvc $url $fileNameTmp
poster=`egrep -o "\"posterarts\"\:\[\"https:[^\".]*canvas-lb.tubitv.com[^\".]*" ../GrabFolder/Tubi/$movTmp | sed 's/\u002F//g' | grep -o https.*`

java -cp ../playright_copy_url.jar PlayrightVideo.PlayrightVideo.TubiDownloadPosterArt npx playwright codegen demo.playwright.dev/todomvc $poster "$fileName" $movSave

rm $fileNameTmp
