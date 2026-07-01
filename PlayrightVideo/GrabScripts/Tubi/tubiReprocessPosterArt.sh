#!/bin/bash
crDir=`pwd`
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

movies=(`diff  <(ls -1 ../../GrabFolder/Tubi/*.url | sed 's/..\/..\/GrabFolder\/Tubi\///g' | sed s/.url//g) <( ls -1 ../../GrabFolder/Tubi/images/*.png | sed 's/..\/..\/GrabFolder\/Tubi\/images\///g' | sed s/.png//g) | egrep "^<" | egrep "^<|^>" | sed "s/[<>]//g"`)
total_count=${#movies[@]}

count=1
parallelCount=3
for mov in ${movies[@]}; 
do
	mov=$mov".url"
	echo $mov
	if [ $(expr $count % $parallelCount) -eq 0 ]
	then
		echo "remaining: " $(( $count )) "/" $total_count
		sleep 9
	else
		./tubiDownloadPosterArt.sh $fileName $fileNameTmp $mov&
	fi

	count=$(( $count + 1))
done
cd $crDir
