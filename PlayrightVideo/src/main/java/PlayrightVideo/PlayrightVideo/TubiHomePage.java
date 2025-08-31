package PlayrightVideo.PlayrightVideo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TubiHomePage 
{
	private static final String url = "https://tubitv.com/";
	
	public static void main(String [] args)
	{
		String sourceFile = args[args.length-1];
		
		try (Playwright playwright = Playwright.create()) 
		{
			Browser browser = playwright.chromium().launch();
			Page page = PlayrightUtil.tubiCollectUrls(browser, url);
			String contentString = page.content();
			page.close();
			
			sourceFile = PlayrightUtil.writeFile(sourceFile, contentString);
			
			System.out.println(contentString);
		}
	}
}
