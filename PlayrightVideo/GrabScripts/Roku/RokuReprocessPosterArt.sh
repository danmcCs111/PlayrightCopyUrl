#!/bin/bash
crDir=`pwd`
cd "$(dirname "$0")"

movies=`diff  <(ls -1 ../../GrabFolder/Roku/ | sed s/.url//g) <( ls -1 ../../GrabFolder/Roku/images/ | sed s/.png//g) | egrep "^<|^>" | sed "s/[<>]//g"`
fileName="C:\\Users\\danie\\codebase\\danmcCs111\\PlayrightCopyUrl\\PlayrightVideo\\GrabFolder\\Roku\\images\\"
fileNameTmp="C:\\Users\\danie\\codebase\\danmcCs111\\PlayrightCopyUrl\\PlayrightVideo\\GrabFolder\\Roku\\tmp\\"


count=1
paralellCount=10
for mov in ${movies[@]}; 
do
	mov=$mov".url"
	echo $mov
	if [ $(expr $count % $paralellCount) -eq 0 ]
	then
		sleep 15
	else
	./rokuDownloadPosterArt.sh $fileName $fileNameTmp $mov&
	fi

	count=$(( $count + 1))
done
cd $crDir
