package PlayrightVideo.PlayrightVideo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.ScreenshotOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public interface PlayrightUtil 
{
	public static Page navigateAndReturnPage(Browser browser, String url)
    {
    	Page page = browser.newPage();
        page.navigate(url);
        page.waitForLoadState();
        return page;
    }
	
	public static Page navigateAndReturnScreenshotPage(Browser browser, String url, String path)
    {
    	Page page = browser.newPage();
        page.navigate(url);
        page.waitForLoadState();
        
        Locator locator = page.getByRole(AriaRole.IMG);
    	ScreenshotOptions screenshotOptions = new ScreenshotOptions();
    	screenshotOptions.setPath(Paths.get(path));
    	 
    	locator.screenshot(screenshotOptions);
        
        return page;
    }
	
	
	
	public static Page tubiCollectUrls(Browser browser, String baseUrl)
    {
    	Page page = browser.newPage();
        page.navigate(baseUrl);
        page.waitForLoadState();
        
        synchronized (page) 
        {
        	for (int i = 0; i < 20; i++) 
        	{
        		page.keyboard().press("PageDown");
        		try {
        			page.wait(3000);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
        	}
		}
        
        return page;
    }
	
	public static Page tubiNavigateAndReturnPage(Browser browser, String url)
    {
    	Page page = browser.newPage();
        page.navigate(url);
        page.waitForLoadState();
        
        synchronized (page) 
        {
        	for (int i = 0; i < 20; i++) 
        	{
        		page.keyboard().press("PageDown");
        		try {
        			page.wait(3000);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
        	}
		}
        
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
