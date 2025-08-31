package PlayrightVideo.PlayrightVideo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TubiSingleCollect 
{
	public static void main(String [] args)
	{
		String
			url = args[args.length-2],
			sourceFile = args[args.length-1];
		
		try (Playwright playwright = Playwright.create()) 
		{
			Browser browser = playwright.chromium().launch();
			Page page = PlayrightUtil.navigateAndReturnPage(browser, url);
			String contentString = page.content();
			page.close();
			
			sourceFile = PlayrightUtil.writeFile(sourceFile, contentString);
			
			System.out.println(contentString);
		}
	}
}
