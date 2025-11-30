#!/bin/bash

fileName=$1
fileNameTmp=$2
mov=$3

movTmp=`echo $mov | sed "s/..\/..\/GrabFolder\/Tubi\///g"`
mov="../../GrabFolder/Tubi/"$movTmp

if [ ! -f $mov ]; then
	echo "file not found! (" $mov ")"
	exit
fi

url="https://"`egrep -o URL=.* $mov | sed 's/URL=//g' | sed 's/^.*://g'`
movSave=`echo $movTmp | sed 's/.url//g'`".png"
fileNameTmp=$fileNameTmp$movTmp

echo $url $movSave $movTmp $mov

java -cp "../../target/dependency/driver-1.53.0.jar;../../target/dependency/driver-bundle-1.53.0.jar;../../target/dependency/error_prone_annotations-2.36.0.jar;../../target/dependency/gson-2.12.1.jar;../../target/dependency/junit-3.8.1.jar;../../target/dependency/opentest4j-1.3.0.jar;../../target/dependency/playwright-1.53.0.jar;../../target/playright-video-0.0.1-SNAPSHOT.jar" PlayrightVideo.PlayrightVideo.TubiSingleCollect npx playwright codegen demo.playwright.dev/todomvc $url $fileNameTmp
poster=`egrep -o "\"posterarts\"\:\[\"https:[^\".]*canvas-lb.tubitv.com[^\".]*" $fileNameTmp | sed 's/\u002F//g' | grep -o https.*`

java -cp "../../target/dependency/driver-1.53.0.jar;../../target/dependency/driver-bundle-1.53.0.jar;../../target/dependency/error_prone_annotations-2.36.0.jar;../../target/dependency/gson-2.12.1.jar;../../target/dependency/junit-3.8.1.jar;../../target/dependency/opentest4j-1.3.0.jar;../../target/dependency/playwright-1.53.0.jar;../../target/playright-video-0.0.1-SNAPSHOT.jar" PlayrightVideo.PlayrightVideo.TubiDownloadPosterArt npx playwright codegen demo.playwright.dev/todomvc $poster "$fileName" $movSave

rm $fileNameTmp
