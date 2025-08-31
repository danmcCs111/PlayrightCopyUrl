#!/bin/bash
cd "$(dirname "$0")"

fileName="../GrabFolder/Tubi/tubiHomePage.txt"
fileName2="../GrabFolder/Tubi/tubiCollector.txt"

java -cp ../playright_copy_url.jar PlayrightVideo.PlayrightVideo.TubiHomePage npx playwright codegen demo.playwright.dev/todomvc "$fileName"

urls=`./tubiUrlStrip.sh "$fileName"`
for a in ${urls[@]};
do
 echo $a
 java -cp ../playright_copy_url.jar PlayrightVideo.PlayrightVideo.TubiCollector npx playwright codegen demo.playwright.dev/todomvc $a $fileName2; ./tubiCollector.sh $fileName2
done
