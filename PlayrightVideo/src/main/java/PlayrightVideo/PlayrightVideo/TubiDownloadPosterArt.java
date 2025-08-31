package PlayrightVideo.PlayrightVideo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TubiDownloadPosterArt 
{
	public static void main(String [] args)
	{
		
		String
			url = args[args.length-3],
			screenShotPath = args[args.length-2],
			screenShotName = args[args.length-1];
	
		try (Playwright playwright = Playwright.create()) 
		{
			Browser browser = playwright.chromium().launch();
			Page page = PlayrightUtil.navigateAndReturnScreenshotPage(browser, url, screenShotPath+screenShotName);
			String contentString = page.content();
			page.close();
			
			System.out.println(contentString);
		}
	}
}
