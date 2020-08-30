package univpm.progetto.controller;


import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.progetto.FiltrieStatistiche.JSONStatistiche;
import univpm.progetto.FiltrieStatistiche.Statistiche;
import univpm.progetto.Json.JSONFinale;
import univpm.progetto.Json.JSONparse;

import univpm.progetto.elaborazione.Searchper;
import univpm.progetto.elaborazione.SearchperMeta;
import univpm.progetto.exception.FailDataException;
import univpm.progetto.exception.FormatoNonTrovatoException;
import univpm.progetto.exception.ParametriErratiException;

@RestController
public class SimpleRestController {
	
	@PostMapping("/search_tipo_dim_altezzalarghezzaMeta")
	public JSONFinale esempio1Meta(@RequestBody String body,@RequestParam (defaultValue = "0")String h,@RequestParam (defaultValue = "0")String w,@RequestParam (defaultValue = ">")String operatore) throws FormatoNonTrovatoException, ParametriErratiException {
		SearchperMeta cerca = new SearchperMeta();
		JSONArray obj2 = cerca.TipoDiFileParametri(body,h,w,operatore);
		Statistiche stats = new Statistiche();
		JSONStatistiche statistiche = new JSONStatistiche();
		statistiche = stats.StatisticaDimensione(obj2);
		JSONFinale finale = new JSONFinale(obj2,statistiche);
		return finale;
	}		
	
	@PostMapping("/search_tipo_dimMeta")
	public JSONFinale esempio2Meta(@RequestBody String body,@RequestParam (defaultValue = "0")String dimens,@RequestParam (defaultValue = ">")String operatore) throws FormatoNonTrovatoException, ParametriErratiException {
		SearchperMeta cerca = new SearchperMeta();
		JSONArray obj2 = cerca.TipoDiFile(body,dimens,operatore);
		Statistiche stats = new Statistiche();
		JSONStatistiche statistiche = new JSONStatistiche();
		statistiche = stats.StatisticaDimensioneMeta(obj2);
		JSONFinale finale = new JSONFinale(obj2,statistiche);
		return finale;
	}		
	
	@PostMapping("/search_dataMeta")
	public JSONArray esempio3Meta(@RequestParam List<String> corpo) throws FailDataException, ParametriErratiException {
		SearchperMeta cerca = new SearchperMeta();
		JSONArray obj2 = cerca.Data(corpo);
		return obj2;
	}
	
	@PostMapping("/search_nomeMeta")
	public JSONArray esempio4Meta(@RequestBody String body) {
		SearchperMeta cerca = new SearchperMeta();
		JSONArray obj2 = cerca.NomeFile(body);
		return obj2;
	}			
	
	@PostMapping("/search_tipo_dim")
	public JSONFinale esempio1(@RequestBody String body,@RequestParam (defaultValue = "0")String dim,@RequestParam (defaultValue = ">")String operatore) throws FormatoNonTrovatoException, ParametriErratiException {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.TipoDiFile(body,dim,operatore);
		Statistiche stats = new Statistiche();
		JSONStatistiche statistiche = new JSONStatistiche();
		statistiche = stats.StatisticaDimensione(obj2);
		JSONFinale finale = new JSONFinale(obj2,statistiche);
		return finale;
	}		
	
	@PostMapping("/search_nome")
	public JSONArray esempio2(@RequestBody String body) {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.NomeFile(body);
		return obj2;
	}		
	
	@PostMapping("/search_data")
	public JSONArray esempio3(@RequestParam List<String> corpo) throws FailDataException, ParametriErratiException {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.Data(corpo);
		return obj2;
	}		
	

}
