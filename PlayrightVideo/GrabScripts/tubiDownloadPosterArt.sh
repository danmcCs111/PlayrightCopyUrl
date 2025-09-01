#!/bin/bash

fileName=$1
fileNameTmp=$2
mov=$3

movTmp=`echo $mov | sed "s/..\/GrabFolder\/Tubi\///g"`
mov="../GrabFolder/Tubi/"$movTmp

if [ ! -f $mov ]; then
	echo "file not found! (" $mov ")"
	exit
fi

url="https://"`egrep -o URL=.* $mov | sed 's/URL=//g' | sed 's/^.*://g'`
movSave=`echo $movTmp | sed 's/.url//g'`".png"
fileNameTmp=$fileNameTmp$movTmp

echo $url $movSave $movTmp $mov

java -cp ../playright_copy_url.jar PlayrightVideo.PlayrightVideo.TubiSingleCollect npx playwright codegen demo.playwright.dev/todomvc $url $fileNameTmp
poster=`egrep -o "\"posterarts\"\:\[\"https:[^\".]*canvas-lb.tubitv.com[^\".]*" $fileNameTmp | sed 's/\u002F//g' | grep -o https.*`

java -cp ../playright_copy_url.jar PlayrightVideo.PlayrightVideo.TubiDownloadPosterArt npx playwright codegen demo.playwright.dev/todomvc $poster "$fileName" $movSave

rm $fileNameTmp
