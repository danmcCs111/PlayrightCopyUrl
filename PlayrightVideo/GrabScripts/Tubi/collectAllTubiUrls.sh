#!/bin/bash
cd "$(dirname "$0")"

source ./java-class-path.sh

fileName="../../GrabFolder/Tubi/tubiHomePage.txt"
fileName2="../../GrabFolder/Tubi/tubiCollector.txt"

java -cp "$java_playright_cp" PlayrightVideo.PlayrightVideo.TubiHomePage npx playwright codegen demo.playwright.dev/todomvc "$fileName"

urls=`./tubiUrlStrip.sh "$fileName"`
for a in ${urls[@]};
do
 echo $a
 java -cp "$java_playright_cp" PlayrightVideo.PlayrightVideo.TubiCollector npx playwright codegen demo.playwright.dev/todomvc $a $fileName2; ./tubiCollector.sh $fileName2
done
