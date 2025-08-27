#!/bin/bash
cd "$(dirname "$0")"
./grabWeather.sh
./parseWeather.sh "`ls -ltr ../GrabFolder/Weather/weather_grab*.html | awk '{print $NF}' | tail -1`"
