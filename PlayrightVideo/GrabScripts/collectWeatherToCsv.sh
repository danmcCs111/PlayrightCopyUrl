#!/bin/bash
cd "$(dirname "$0")"
fle="$1"
zip="$2"
if [[ -z "$fle" || -z "$zip" ]]
then
	echo "no file or zip passed."
	exit
fi
./grabWeather.sh "$fle" "$zip"
./parseWeather.sh "$fle" "$zip"
