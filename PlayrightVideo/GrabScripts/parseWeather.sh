#!/bin/bash
fle="../GrabFolder/Weather/weather_grab*"
egrep -o "<b>.*Temperature: [0-9]+ .*Thunder: .* onclick\=" $fle | sed 's/&amp;//g' | sed 's/nbsp;/ /g' | sed 's/<[/]*b[r]*>/ /g' | sed "s/')\" onclick=//g"
