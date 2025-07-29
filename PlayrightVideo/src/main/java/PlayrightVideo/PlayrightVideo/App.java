package PlayrightVideo.PlayrightVideo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class App 
{
    public static void main(String[] args) 
    {
    	String 
    		url = args[args.length-2],
    		sourceFile = args[args.length-1];
    	
        try (Playwright playwright = Playwright.create()) 
        {
        	LaunchOptions lo = new LaunchOptions();
            Browser browser = playwright.firefox().launch(lo);
            String writeString = navigateAndReturnContent(browser, url);
            writeFile(sourceFile, writeString);
        }
    }
    
    private static String navigateAndReturnContent(Browser browser, String url)
    {
    	Page page = browser.newPage();
        page.navigate(url);
        page.waitForLoadState();
        System.out.println(page.content());
        System.out.println(page.url());
        
        return page.content();
    }
    
    private static void writeFile(String sourceFile, String writeString)
    {
    	Calendar cal = Calendar.getInstance();
    	String suffix = "_";
    	suffix += cal.get(Calendar.YEAR) + "_";
    	suffix += cal.get(Calendar.MONTH) + 1 + "_";
    	suffix += cal.get(Calendar.DAY_OF_MONTH)+ "_";
    	suffix += cal.get(Calendar.HOUR)+ "_";
    	suffix += cal.get(Calendar.MINUTE);
    	
    	if(!sourceFile.endsWith(".html"))
    	{
    		sourceFile += ".html";
    	}
    	
    	sourceFile = sourceFile.replace(".html", suffix + ".html");
    	
    	try {
			FileWriter fw = new FileWriter(sourceFile);
			fw.write(writeString);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}