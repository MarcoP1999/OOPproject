package univpm.progetto.FiltrieStatistiche.statistiche;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.progetto.FiltrieStatistiche.JSONStatistiche;

/**
 * Tale classe contiene i metodi per il calcolo delle statistiche in base alle APIs utilizzate 
 * @author Proietti Marco
 * @author Traini Davide
 *
 */

public class Statistiche {
	 
	
	/**
	 * Tale metodo viene invocato se l'API utilizzata per il parsing è search
	 * @param obj è un JSONArray contenente gli elementi da cui estrarre le statistiche 
	 * @return JSONStatisctiche contenente statistiche legate alle dimensioni
	 */
	
	public JSONStatistiche StatisticaDimensione(JSONArray obj) {
		
		JSONObject meta = new JSONObject();
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		Long dimensione = (long) 0;
		
		// se l'array è vuoto non restituisco statistiche
		if (obj.isEmpty()) return null;
		
		meta = (JSONObject) obj.get(0);
		metadata1 = (JSONObject) meta.get("metadata");
		metadata2 = (JSONObject) metadata1.get("metadata");
		dimensione = (Long) metadata2.get("size");
		
    	Long minima = dimensione;
	    Long massima = dimensione;
	    Long somma = dimensione;
	    Double mediageo = Math.pow(dimensione,1/obj.size());
	    Double root = (double) 0;
	    
		for (int i=1;i<obj.size();i++) {
			meta = (JSONObject) obj.get(i);
			metadata1 = (JSONObject) meta.get("metadata");
			metadata2 = (JSONObject) metadata1.get("metadata");
			dimensione = (Long) metadata2.get("size");
			
			if (dimensione<minima)
			minima = dimensione;
			
			if (dimensione>massima)
			massima = dimensione;

			somma += dimensione;			
			
			root = Math.pow(dimensione,1/obj.size());
			mediageo = mediageo*root;	
		}
		
		Double mediamat = (double) (somma/obj.size());
		// costruisco un oggetto JSONStatistiche passando come parametri le caratteristiche calcolate
		JSONStatistiche Json = new JSONStatistiche(minima,mediamat,mediageo,massima);
		
		return Json;
		
	}
	
	
	/**
	 * Tale metodo viene invocato se l'API utilizzata per il parsing è get_metadata
	 * @param obj è un JSONArray contenente gli elementi da cui estrarre le statistiche 
	 * @return JSONStatisctiche contenente statistiche legate alle dimensioni
	 */
	
public JSONStatistiche StatisticaDimensioneMeta(JSONArray obj) {
		
		JSONObject meta = new JSONObject();

		Long dimensione = (long) 0;
		
		if (obj.isEmpty()) return null;
		


		meta = (JSONObject) obj.get(0);
		dimensione = (Long) meta.get("size");
		
		Long minima = dimensione;
	    Long massima = dimensione;
	    Long somma = dimensione;
	    Double mediageo = Math.pow(dimensione,1/obj.size());
	    Double root = (double) 0;
	    
		for (int i=1;i<obj.size();i++) {
			meta = (JSONObject) obj.get(i);
			dimensione = (Long) meta.get("size");
			
			if (dimensione<minima)
			minima = dimensione;
			
			if (dimensione>massima)
			massima = dimensione;

			somma += dimensione;			
			
			root = Math.pow(dimensione,1/obj.size());
			mediageo = mediageo*root;	

		}
		
		Double mediamat = (double) (somma/obj.size());
		
		// costruisco un oggetto JSONStatistiche passando come parametri le caratteristiche calcolate
		JSONStatistiche Json = new JSONStatistiche(minima,mediamat,mediageo,massima);
		
		return Json;
		
	}

}
