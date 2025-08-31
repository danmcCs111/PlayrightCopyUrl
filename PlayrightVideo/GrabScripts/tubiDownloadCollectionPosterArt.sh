#!/bin/bash

fileName="C:\\Users\\danie\\codebase\\danmcCs111\\PlayrightCopyUrl\\PlayrightVideo\\GrabFolder\\Tubi\\images\\"
fileNameTmp="C:\\Users\\danie\\codebase\\danmcCs111\\PlayrightCopyUrl\\PlayrightVideo\\GrabFolder\\Tubi\\"

#movies=`ls *.url | xargs egrep -o URL=.* | sed 's/URL=//g' | sed 's/^.*://g'`

movies=`ls ../GrabFolder/Tubi/*.url`

count=1
paralellCount=10
for mov in ${movies[@]}; 
do
	if [ $(expr $count % $paralellCount) -eq 0 ]
	then
		sleep 15
	else
	./tubiDownloadPosterArt.sh $fileName $fileNameTmp $mov&
	fi

	count=$(( $count + 1))
done
