package ktbyte.assistant.catApp;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;

public class CatApp extends App{
	
	public List<Action> getActions(){
		return Arrays.asList(new GetCat());
	}
}

