#/bin/bash
cd "$(dirname "$0")"
java -jar ../playright_copy_url.jar npx playwright codegen demo.playwright.dev/todomvc "https://forecast.weather.gov/MapClick.php?lat=39.9889&lon=-82.9874&unit=0&lg=english&FcstType=graphical" "C:\Program Files\Git\git-bash.exe" "-c" "-i" "/c/Users/danie/codebase/danmcCs111/PlayrightCopyUrl/PlayrightVideo/GrabScripts/parseWeather.sh" "C:\Users\danie\codebase\danmcCs111\PlayrightCopyUrl\PlayrightVideo\GrabFolder\Weather\weather_grab.html"
