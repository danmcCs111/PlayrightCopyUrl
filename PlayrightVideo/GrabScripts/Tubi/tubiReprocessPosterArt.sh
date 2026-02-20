#!/bin/bash
crDir=`pwd`
cd "$(dirname "$0")"

movies=`diff  <(ls -1 ../../GrabFolder/Tubi/ | sed s/.url//g) <( ls -1 ../../GrabFolder/Tubi/images/ | sed s/.png//g) | egrep "^<|^>" | sed "s/[<>]//g"`
fileName="..\\..\\GrabFolder\\Tubi\\images\\"
fileNameTmp="..\\..\\GrabFolder\\Tubi\\tmp\\"


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
	./tubiDownloadPosterArt.sh $fileName $fileNameTmp $mov&
	fi

	count=$(( $count + 1))
done
cd $crDir
