package univpm.progetto.Json;

import org.json.simple.JSONArray;

import univpm.progetto.FiltrieStatistiche.JSONStatistiche;

public class JSONFinale {
	
	public JSONArray elementi;
	public JSONStatistiche statistiche;
	
	public JSONFinale(JSONArray elementi, JSONStatistiche statistiche) {
		super();
		this.elementi = elementi;
		this.statistiche = statistiche;
	}
	

}
