package PlayrightVideo.PlayrightVideo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ZipToGeoCode 
{
	private static final String 
		URL = "https://gps-coordinates.org/zip-code-to-coordinates.php",
		TEXT_BOX = "input#zipcode_lookup",
		BUTTON_LOOKUP = "#btnGetGpsCoordinates",
		LATITUDE_LONGITUDE = "#latlong",
		ZIP = "#zip";
	
    public static void main(String[] args)
    {
    	String zipStr = args[args.length-1];
    	String [] latLong = collectCoordinates(zipStr);
    	System.out.println("Latitude Longitude: " + latLong[0] + " " + latLong[1]);
    }
    
	public static String [] collectCoordinates(String zipStr)
	{
		String [] latLongRet = new String[2];
		try (Playwright playwright = Playwright.create()) 
		{
			LaunchOptions lo = new LaunchOptions();
			Browser browser = playwright.firefox().launch(lo);
			Page page = App.navigateAndReturnPage(browser, URL);
			Locator l = page.locator(TEXT_BOX);
			l.fill(zipStr);
			
			System.out.println("Zip Code: " + l.inputValue());
			Locator l2 = page.locator(BUTTON_LOOKUP);
			l2.click();
			page.waitForURL(URL);
			page.waitForLoadState();
			
			Locator l4 = page.locator(ZIP);
			l4.waitFor();
			
			Locator l3 = page.locator(LATITUDE_LONGITUDE);
			String retStr = "";
			for(String s : l3.allTextContents())
				retStr = s;
			latLongRet = retStr.split(",");
		}
		
		return latLongRet;
	}
    
}