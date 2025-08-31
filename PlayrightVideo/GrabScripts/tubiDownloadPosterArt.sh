#!/bin/bash

fileName="C:\\Users\\danie\\codebase\\danmcCs111\\PlayrightCopyUrl\\PlayrightVideo\\GrabFolder\\Tubi\\images\\"
fileNameTmp="C:\\Users\\danie\\codebase\\danmcCs111\\PlayrightCopyUrl\\PlayrightVideo\\GrabFolder\\Tubi\\tmp.txt"

#movies=`ls *.url | xargs egrep -o URL=.* | sed 's/URL=//g' | sed 's/^.*://g'`

movies=`ls ../GrabFolder/Tubi/*.url`

for mov in ${movies[@]}; 
do
	url=https://`egrep -o URL=.* $mov | sed 's/URL=//g' | sed 's/^.*://g'`
	movSave=`echo $mov | sed "s/..\/GrabFolder\/Tubi\///g"`.png
	echo $url $movSave
	java -cp ../playright_copy_url.jar PlayrightVideo.PlayrightVideo.TubiSingleCollect npx playwright codegen demo.playwright.dev/todomvc $url $fileNameTmp
	poster=`egrep -o "\"posterarts\"\:\[\"https:[^\".]*canvas-lb.tubitv.com[^\".]*" ../GrabFolder/Tubi/tmp.txt | sed 's/\u002F//g' | grep -o https.*`

	java -cp ../playright_copy_url.jar PlayrightVideo.PlayrightVideo.TubiDownloadPosterArt npx playwright codegen demo.playwright.dev/todomvc $poster "$fileName" $movSave
done

