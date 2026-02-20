#!/bin/bash
cd "$(dirname "$0")"

source ./java-class-path.sh

fileName="../../GrabFolder/Tubi/tubiHomePage.txt"
fileName2="../../GrabFolder/Tubi/tubiCollector.txt"

function run()
{
	java -cp "$java_playright_cp" PlayrightVideo.PlayrightVideo.TubiHomePage npx playwright codegen demo.playwright.dev/todomvc "$fileName"

	urls=(`tubiUrlStrip "$fileName"`)
	array_count=${#urls[@]}
	count=$(( 1 ))
	for a in ${urls[@]};
	do
	 echo "category count: " $count " of " $array_count " category: " $a
	 java -cp "$java_playright_cp" PlayrightVideo.PlayrightVideo.TubiCollector npx playwright codegen demo.playwright.dev/todomvc $a $fileName2; tubiCollector $fileName2
	 count=$(( $count + 1 ))
	done
}

function tubiUrlStrip()
{
	fileName="$1"
	egrep -o "href=\"/category/[/a-z_^\"]+" $fileName | sed 's/href=//g' | awk '{system(" echo https://tubitv.com" $NF)}'
}

function tubiCollector()
{
	fileName="$1"
	files=(`egrep -o "tubitv.com/movies/[0-9]*/[^\".]*" $fileName`)
	for f in ${files[@]}
	do
		toUrl "$f"
	done
}

function toUrl()
{
	url=$1
	fileName="../../GrabFolder/Tubi/"`echo $url | egrep -o "/[^\/.*]*$" | sed 's/\///g'`.url
	echo $fileName
	echo [InternetShortcut] > $fileName
	echo "URL="$url >> $fileName
}

run