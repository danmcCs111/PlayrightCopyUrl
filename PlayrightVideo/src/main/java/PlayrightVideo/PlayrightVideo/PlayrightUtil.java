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
	public static int
		PAGE_LOAD_WAIT = 3000,//TODO
		PAGE_CLICK_WAIT = 1500,
		PAGE_KEY_WAIT = 1500;
	
	public static Page navigateWithClass(Page page, String clazzText)
	{
		try {
			page.wait(PAGE_LOAD_WAIT);
			Locator loc2 = page.locator(clazzText);
			loc2.click();
			page.wait(PAGE_CLICK_WAIT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page.waitForLoadState();
		
		return page;
	}
	
	public static Page navigateWithClickText(Page page, String text)
	{
		try {
			page.wait(PAGE_LOAD_WAIT);
			Locator loc2 = page.getByRole(AriaRole.BUTTON).getByText(text);
			loc2.click();
			page.wait(PAGE_CLICK_WAIT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page.waitForLoadState();
		
		return page;
	}
	
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
        			page.wait(PAGE_KEY_WAIT);
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
        			page.wait(PAGE_KEY_WAIT);
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
