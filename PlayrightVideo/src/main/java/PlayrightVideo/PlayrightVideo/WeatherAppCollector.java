package PlayrightVideo.PlayrightVideo;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class WeatherAppCollector
{
	private static final String 
		COMMAND_EXEC = "C:\\Program Files\\Git\\git-bash.exe",//TODO. OS detection
		OPTION1 = "-c"; 
	
    public static void main(String[] args) 
    {
    	String 
    		url = args[args.length-4],
    		command = args[args.length-3],
    		sourceFile = args[args.length-2],
    		zipCode = args[args.length-1];
    	
    	String [] latLong = ZipToGeoCode.collectCoordinates(zipCode);
    	url = url.replaceFirst("lat=&", "lat=" + latLong[0].strip() + "&");
    	url = url.replaceFirst("lon=&", "lon=" + latLong[1].strip() + "&");
    	System.out.println(boolean.class);
        try (Playwright playwright = Playwright.create()) 
        {
        	LaunchOptions lo = new LaunchOptions();
            Browser browser = playwright.firefox().launch(lo);
            String contentString = PlayrightUtil.navigateAndReturnPage(browser, url).content();
            sourceFile = PlayrightUtil.writeFile(sourceFile, contentString);
            PlayrightUtil.buildCsv(new String [] {COMMAND_EXEC, OPTION1, command+sourceFile});
        }
    }
    
}