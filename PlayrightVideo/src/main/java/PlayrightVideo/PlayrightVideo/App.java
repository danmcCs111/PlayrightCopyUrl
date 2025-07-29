package PlayrightVideo.PlayrightVideo;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class App 
{
    public static void main(String[] args) 
    {
        try (Playwright playwright = Playwright.create()) 
        {
        	LaunchOptions lo = new LaunchOptions();
            Browser browser = playwright.firefox().launch(lo);
            navigate(browser, "https://forecast.weather.gov/MapClick.php?lat=39.9889&lon=-82.9874&unit=0&lg=english&FcstType=graphical");
        }
    }
    
    public static void navigate(Browser browser, String url)
    {
    	Page page = browser.newPage();
        page.navigate(url);
        page.waitForLoadState();
        System.out.println(page.content());
        System.out.println(page.url());
        
    }
    
}