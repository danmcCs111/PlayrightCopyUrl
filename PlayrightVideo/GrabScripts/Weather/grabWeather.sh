#/bin/bash
cd "$(dirname "$0")"

source ./java-class-path.sh

fle="$1"
zip="$2"
if [[ -z "$fle" || -z "$zip" ]]
then
	echo "no file or zip passed."
	exit
fi
echo $fle
java -cp "$java_playright_cp" PlayrightVideo.PlayrightVideo.WeatherAppCollector npx playwright codegen demo.playwright.dev/todomvc "https://forecast.weather.gov/MapClick.php?lat=&lon=&unit=0&lg=english&FcstType=graphical" "/c/Users/danie/codebase/danmcCs111/PlayrightCopyUrl/PlayrightVideo/GrabScripts/parseWeather.sh" "$fle" "$zip"
