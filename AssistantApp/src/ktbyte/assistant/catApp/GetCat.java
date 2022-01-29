package ktbyte.assistant.catApp;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class GetCat extends Action{
	private static final String API_KEY = "5af3c508-59f4-47f0-92e6-db64dfcc3f28";

	@Override
	public void doCommand(String command) {
		HttpRequest req = null;
		
		req = Unirest.get("https://api.thecatapi.com/v1/images/search")
					.queryString("appid", API_KEY);		
	    System.out.println(req.getUrl());
		try {
		  HttpResponse<JsonNode> boom = req.asJson();
		  System.out.println(boom);
			JsonNode node = boom.getBody();
			handleResult(node);
		} catch (UnirestException e) {
			System.out.println("request error occurred: " + e);
		}
	}
	
	

	private void handleResult(JsonNode node) {
		Assistant assistant = Assistant.getInstance();
		
		JSONObject json = node.getArray().getJSONObject(0);
		System.out.println("test " +node);
		String url = json.optString("url");
		
		
		assistant.displayItem(new Response(url));
		
	}



	@Override
	public double getLikelihood(String command) {		
		if(command.contains("cat")) {
			return 2.4;
		}
		if(command.contains("cats")) {
			return 2.4;
		}
		if(command.contains("picture")) {
			return 2.3;
		}
		if(command.contains("pictures")) {
			return 2.3;
		}
		
		
		return 0;
	}
}