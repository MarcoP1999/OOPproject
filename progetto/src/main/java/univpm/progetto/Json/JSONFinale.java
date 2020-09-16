package univpm.progetto.Json;

import org.json.simple.JSONArray;

import univpm.progetto.filtri_e_statistiche.JSONStatistiche;

/**
 * Classe che rappresenta il JSON che viene restituito al client comprendente elementi 
 * ricercati + statistiche relative ad essi
 * 
 * @author Proietti Marco
 * @author Traini Davide
 */

public class JSONFinale {
	
	public JSONArray elementi;
	public JSONStatistiche statistiche;
	
	public JSONFinale(JSONArray elementi, JSONStatistiche statistiche) {
		super();
		this.elementi = elementi;
		this.statistiche = statistiche;
	}
	

}
