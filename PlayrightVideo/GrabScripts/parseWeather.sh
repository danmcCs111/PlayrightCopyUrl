#!/bin/bash
fle="../GrabFolder/Weather/weather_grab*"
egrep -o "<b>.*Temperature: [0-9]+ .*Thunder: [A-Za-z ]* [-\)\(0-9\% ]*" $fle | sed 's/&amp;//g' | sed 's/nbsp;/ /g' | sed 's/<[/]*b[r]*>/ /g'
