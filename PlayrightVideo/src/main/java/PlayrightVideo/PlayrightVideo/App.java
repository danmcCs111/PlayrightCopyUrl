package PlayrightVideo.PlayrightVideo;

import java.io.FileWriter;
import java.io.IOException;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class App 
{
	private static final String 
		COMMAND_EXEC = "C:\\Program Files\\Git\\git-bash.exe",
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
    	
        try (Playwright playwright = Playwright.create()) 
        {
        	LaunchOptions lo = new LaunchOptions();
            Browser browser = playwright.firefox().launch(lo);
            String contentString = navigateAndReturnPage(browser, url).content();
            sourceFile = writeFile(sourceFile, contentString);
            buildCsv(new String [] {COMMAND_EXEC, OPTION1, command+sourceFile});
        }
    }
    
    public static Page navigateAndReturnPage(Browser browser, String url)
    {
    	Page page = browser.newPage();
        page.navigate(url);
        page.waitForLoadState();
        return page;
    }
    
    public static String writeFile(String sourceFilePath, String contentString)
    {
    	try {
			FileWriter fw = new FileWriter(sourceFilePath);
			fw.write(contentString);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	sourceFilePath = " " + sourceFilePath.replaceAll("\\\\", "/").replaceAll("C:", "/c");
    	System.out.println(sourceFilePath);
    	
    	return sourceFilePath;
    }
    
    public static void buildCsv(String ... commandArgs)
    {
    	for(String s : commandArgs)
    		System.out.println(s);
    	try {
			CommandExecutor.executeProcess(true, commandArgs);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}