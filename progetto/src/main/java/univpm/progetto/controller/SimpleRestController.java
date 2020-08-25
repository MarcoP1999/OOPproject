package univpm.progetto.controller;


import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import univpm.progetto.Json.JSONparse;
import univpm.progetto.Json.Meta;
import univpm.progetto.elaborazione.Searchper;

@RestController
public class SimpleRestController {
	
	@PostMapping("/search_dim")
	public JSONArray esempio1(@RequestBody Long body) {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.Dimensione(body);
		return obj2;
	}		
	
	
	@PostMapping("/search_tipo_dim")
	public JSONArray esempio2(@RequestBody String body,@RequestParam (defaultValue = "0")String dim,@RequestParam (defaultValue = ">")char operatore) {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.TipoDiFile(body,dim,operatore);
		return obj2;
	}		
	
	@PostMapping("/search_nome")
	public JSONArray esempio3(@RequestBody String body) {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.NomeFile(body);
		return obj2;
	}		
	
	@PostMapping("/search_data")
	public JSONArray esempio4(@RequestParam List<String> corpo) {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.Data(corpo);
		return obj2;
	}		
	

}
