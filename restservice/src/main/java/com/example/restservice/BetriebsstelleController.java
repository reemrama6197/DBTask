package com.example.restservice;

import java.io.IOException;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BetriebsstelleController {

	
	@GetMapping("/betriebsstelle/{id}") 
	@ResponseBody
	public ResponseEntity<String> getDetails(@PathVariable String id) throws JSONException, IOException
	{
		Betriebsstelle searchObject = new Betriebsstelle(id);
		// result is used to distinguish whether we have an entry with id provided or not
		String result = searchObject.searchForBetriebsstelle(id);
		
		if(!result.equals("null"))
		{
			// update http status with code 200 and display json object
			return new ResponseEntity<>(result, 
					   HttpStatus.OK);
		}
		else
		{
			// update http with code 404 not found and display not found message
			 return ResponseEntity.status(HttpStatus.NOT_FOUND)
			            .body("Die gesuchte betriebsstelle konnte nicht gefunden werden.");
		}
	}
		
}
