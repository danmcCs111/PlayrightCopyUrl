#!/bin/bash
cd "$(dirname "$0")"

fileName="../../GrabFolder/Tubi/tubiHomePage.txt"
fileName2="../../GrabFolder/Tubi/tubiCollector.txt"

java -cp "../../target/dependency/driver-1.53.0.jar;../../target/dependency/driver-bundle-1.53.0.jar;../../target/dependency/error_prone_annotations-2.36.0.jar;../../target/dependency/gson-2.12.1.jar;../../target/dependency/junit-3.8.1.jar;../../target/dependency/opentest4j-1.3.0.jar;../../target/dependency/playwright-1.53.0.jar;../../target/playright-video-0.0.1-SNAPSHOT.jar" PlayrightVideo.PlayrightVideo.TubiHomePage npx playwright codegen demo.playwright.dev/todomvc "$fileName"

urls=`./tubiUrlStrip.sh "$fileName"`
for a in ${urls[@]};
do
 echo $a
 java -cp "../../target/dependency/driver-1.53.0.jar;../../target/dependency/driver-bundle-1.53.0.jar;../../target/dependency/error_prone_annotations-2.36.0.jar;../../target/dependency/gson-2.12.1.jar;../../target/dependency/junit-3.8.1.jar;../../target/dependency/opentest4j-1.3.0.jar;../../target/dependency/playwright-1.53.0.jar;../../target/playright-video-0.0.1-SNAPSHOT.jar" PlayrightVideo.PlayrightVideo.TubiCollector npx playwright codegen demo.playwright.dev/todomvc $a $fileName2; ./tubiCollector.sh $fileName2
done
