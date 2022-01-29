package ktbyte.assistant.toDoList;


import java.util.ArrayList;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class CreateToDoList extends Action{
	Assistant assistant = Assistant.getInstance();
	ArrayList <String> toDoAction = new ArrayList<String>();
	public void doCommand(String command) {
		
		int capacityChecker = 1;
		
		boolean actions = false;
		
//		ArrayList <String> toDoTime = new ArrayList<String>();
		
		if(command.contains("add")) {
			assistant.displayItem(new Response("Item added to the to do list: " + getAction(command) /* + " at " + getTime(command)*/));
			
			capacityChecker++;
				
//			toDoTime.ensureCapacity(capacityChecker);
			toDoAction.ensureCapacity(capacityChecker);
				
			toDoAction.add(getAction(command)); 
//			toDoTime.add(getTime(command));
			
			
			assistant.displayItem(new Response("Current list of things to do:"));
			for(int i = 0; i < toDoAction.size(); i++) {
				assistant.displayItem(new Response(toDoAction.get(i)));
			}
			
			actions = true;
			
		}
		
		if(command.contains("display")) {
			assistant.displayItem(new Response("Current to do list:"));
			for(int i = 0; i < toDoAction.size(); i++) {
				assistant.displayItem(new Response(toDoAction.get(i)));
			}
			actions = true;
			
		}
		
		if(command.contains("remove")) {
			
			String toRemove = taskRemover(command);
			
			boolean sucess = false;
			
			for(int i = 0; i < toDoAction.size(); i++) {
				if(toDoAction.get(i).equals(toRemove)) {
					toDoAction.remove(i);
					
					assistant.displayItem(new Response("Successfully cleared!"));
					
					assistant.displayItem(new Response("Updated list of things to do:"));
					for(int i1 = 0; i1 < toDoAction.size(); i1++) {
						assistant.displayItem(new Response(toDoAction.get(i1)));
					}
					
					sucess = true;
					
				}
			}
			if(sucess == false) {
				assistant.displayItem(new Response("The removal was unsucessful"));
			}
			
			actions = true;
			
		}
		
		if(actions == false) {
			assistant.displayItem(new Response("Please input a command, (add (item), display, or remove(item)"));
		}
		
//		
//		LocalTime local = LocalTime.now();
//		
//		int hour = local.getHour();
//		int minute = local.getMinute();
//		
//		String time = hour + ":" + minute;
//		
//		for(int i = 0; i < toDoTime.size(); i++) {
//			if(toDoTime.get(i).equals(time)){
//				assistant.displayItem(new Response("Your reminder to " + toDoAction.get(i) + " at " + toDoTime.get(i) + " is up"));
//			}
//		}
	}
	
	
	public double getLikelihood(String command) {
		
		
		
		if(command.contains("list")) {
			return 6.5;
		}
		if(command.contains("to")) {
			return 2.5;
		}
		if(command.contains("reminder")) {
			return 4;
		}
		if(command.contains("notification")) {
			return 5;
		}
		
		
		return 0;
	}
	
	String getAction(String command) {
		int actionloc = bestAct(command);
		
		String action = formatting(actionloc, command);
		
		return action;
	}
	
	String taskRemover(String command) {
		int location = RemoveAction(command);
		return formatting(location, command);
		

	}
	
	public int RemoveAction(String command) {
		
		String words[] = command.split(" ");
		
		for(int i = 1; i < words.length; i++) {
			if(words[i].contentEquals("remove")) {
				return i;
			}
		}
		
		return words.length;
	}

	
	
	public int bestAct(String command) {
			
//		String [] keyWords = new String[] {"to", "do", "a"};
//
//			if(command.contains(keyWords[2])) {
//				return command.indexOf(keyWords[2]);
//			}
//			if(command.contains(keyWords[1])) {
//				return command.indexOf(keyWords[1]);
//			}
//			if(command.contains(keyWords[0])) {
//				return command.indexOf(keyWords[0]);
//			}
//			else {
//				return 0;
//			}
		
		String words[] = command.split(" ");
		
		for(int i = 1; i < words.length; i++) {
			if(words[i].contentEquals("add")) {
				return i;
			}
		}
		
		return words.length;
		
		
	}
	
	
	public String formatting(int location, String command) {
		
		String words[] = command.split(" ");
		
		if(location >= words.length) {
			System.out.println(location);
			return "unknown";
		}
		
		if(location < words.length - 1) {
			location++;
		}
		
		return words[location];
		
	}
	
	
	
//		String getTime(String command) {
//		
//		int hour = 0;
//		int minute = 0;
//		
//		
//		if(command.contains(":")) {
//			int location = command.indexOf(":");
//			
//			hour = Integer.parseInt(command.substring(location - 2, location));
//					
//			minute = Integer.parseInt(command.substring(location+1, command.length()));
//			
//		}
//		
//		else {
//			return "[Unable to find time. Please check that it is in this format, 12:59]";
//		}
//		
//		
//		
//		return hour + ":" + minute;
//		
//	}
	
	
	
}
