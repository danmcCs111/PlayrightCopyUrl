package PlayrightVideo.PlayrightVideo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page;

public class TubiCollector 
{
	private static final String url = "https://tubitv.com/category/recommended_for_you";
	
	public static void main(String [] args)
	{
		try (Playwright playwright = Playwright.create()) 
		{
			Browser browser = playwright.chromium().launch();
			Page page = PlayrightUtil.tubiNavigateAndReturnPage(browser, url);
			String contentString = page.content();
			page.close();
			
			System.out.println(contentString);
		}
	}
}
