package PlayrightVideo.PlayrightVideo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ZipToGeoCode 
{
	private static final String 
		url = "https://gps-coordinates.org/zip-code-to-coordinates.php",
		textBox = "input#zipcode_lookup",
		btnLookup = "#btnGetGpsCoordinates",
		latLong = "#latlong",
		zip = "#zip";
	
    public static void main(String[] args)
    {
    	String zipStr = args[args.length-1];
    	
    	try (Playwright playwright = Playwright.create()) 
    	{
    		LaunchOptions lo = new LaunchOptions();
    		Browser browser = playwright.firefox().launch(lo);
    		Page page = App.navigateAndReturnPage(browser, url);
    		String [] latLong = collectCoordinates(page, zipStr);
    		System.out.println(latLong[0] + " " + latLong[1]);
    	}
    }
    
    
    public static String [] collectCoordinates(Page page, String zipStr)
    {
    	Locator l = page.locator(textBox);
		l.fill(zipStr);
		
		System.out.println(l);
		System.out.println(l.inputValue());
		Locator l2 = page.locator(btnLookup);
		System.out.println(l2);
		for(String s : l2.allTextContents())
			System.out.println(s);
		l2.click();
		page.waitForURL(url);
		page.waitForLoadState();
		
		Locator l4 = page.locator(zip);
		l4.waitFor();
		
		Locator l3 = page.locator(latLong);
		String retStr = "";
		for(String s : l3.allTextContents())
			retStr = s;
		String [] ss = retStr.split(",");
		
		return ss;
    }
    
  }