package ktbyte.assistant.app.time;

import java.time.LocalTime;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class GetTimeAction extends Action {
	public void doCommand(String command) {
		Assistant assistant = Assistant.getInstance();
		
		String result = LocalTime.now().toString();
		
		assistant.displayItem(new Response("Local Time: " + result));
	}
	
	public double getLikelihood(String command) {
		
		//TODO: Check if the command given contains words like "Time", "Clock", or others. Return results based on the command.
		
		String [] keyWords = new String[] {"time", "clock", "date"};
		
		
		
		if(command.contains(keyWords[0])) {
			return 4.5;
		}
		if(command.contains(keyWords[1])) {
			return 1.5;
		}
		if(command.contains(keyWords[2])) {
			return 0.8;
		}
		
		
		return 0;
	}
}
