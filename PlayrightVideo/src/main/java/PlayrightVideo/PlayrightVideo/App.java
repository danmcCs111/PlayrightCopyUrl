package PlayrightVideo.PlayrightVideo;

import java.io.FileWriter;
import java.io.IOException;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class App 
{
    public static void main(String[] args) 
    {
    	String 
    		url = args[args.length-6],//TODO use a command line builder. consider integrating with other "ApplicationBuilder" project.
    		command = args[args.length-5],
    		command2 = args[args.length-4],
    		command3 = args[args.length-3],
    		command4 = args[args.length-2],
    		sourceFile = args[args.length-1];
    	
        try (Playwright playwright = Playwright.create()) 
        {
        	LaunchOptions lo = new LaunchOptions();
            Browser browser = playwright.firefox().launch(lo);
            String contentString = navigateAndReturnPage(browser, url).content();
            sourceFile = writeFile(sourceFile, contentString);
            buildCsv(new String [] {command, command2, command3, command4+sourceFile});
        }
    }
    
    public static Page navigateAndReturnPage(Browser browser, String url)
    {
    	Page page = browser.newPage();
        page.navigate(url);
        page.waitForLoadState();
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