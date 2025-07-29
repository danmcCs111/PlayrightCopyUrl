package PlayrightVideo.PlayrightVideo;

import java.io.IOException;

public interface CommandExecutor 
{
	public static void executeProcess(String ... commandArgs) throws IOException
	{
		executeProcess(false, commandArgs);
	}
	
	public static void executeProcess(boolean haltTillComplete, String ... commandArgs) throws IOException
	{
		Process runningProcess = null;
		ProcessBuilder pb = new ProcessBuilder(commandArgs);
		runningProcess = pb.start();
		if(haltTillComplete)
		{
			while(runningProcess.isAlive())
			{
				try {
					Thread.sleep(1000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}