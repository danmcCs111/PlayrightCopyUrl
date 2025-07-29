#!/bin/bash
fle="$1"
if [ -z $fle ]
then
	echo "no file passed."
	exit
fi
fle_save="`ls $1 | sed 's/.html/.csv/g'`"
egrep -o "<b>.*Temperature: [0-9]+ .*Thunder: .* onclick\=" $fle | sed 's/,/ -/g' | sed 's/&amp;/,/g' | sed 's/nbsp;/,/g' | sed -e "s/[,]\{5,\}/\",\"/g" | sed 's/<[/]*b[r]*>/ /g' | sed "s/')\" onclick=//g" | sed -e 's/^/\"/g' | sed -e 's/$/\"/g' > $fle_save
