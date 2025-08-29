#!/bin/bash
cd "$(dirname "$0")"
fle="$1"
if [ -z $fle ]
then
	echo "no file passed."
	exit
fi
./grabWeather.sh "$fle"
./parseWeather.sh "$fle"
