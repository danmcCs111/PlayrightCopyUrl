#!/bin/bash

cd "$(dirname "$0")"

typeOs=`uname`
if [[ "$typeOs" == "Linux" ]]
then
	fileName="../../GrabFolder/Tubi/images/"
	fileNameTmp="../../GrabFolder/Tubi/tmp/"
else
	fileName="..\\..\\GrabFolder\\Tubi\\images\\"
	fileNameTmp="..\\..\\GrabFolder\\Tubi\\tmp\\"
fi

movies=`ls ../../GrabFolder/Tubi/*.url`

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
