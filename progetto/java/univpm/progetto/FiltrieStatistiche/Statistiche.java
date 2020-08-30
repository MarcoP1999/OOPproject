package univpm.progetto.FiltrieStatistiche;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Statistiche {
	 
	
	public JSONStatistiche StatisticaDimensione(JSONArray obj) {
		
		JSONObject meta = new JSONObject();
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		Long dimensione = (long) 0;
		
		if (obj.isEmpty()) return null;
		
		meta = (JSONObject) obj.get(0);
		metadata1 = (JSONObject) meta.get("metadata");
		metadata2 = (JSONObject) metadata1.get("metadata");
		dimensione = (Long) metadata2.get("size");
		
    	Long minima = dimensione;
	    Long massima = dimensione;
	    Long totale = dimensione;
		
		for (int i=1;i<obj.size();i++) {
			meta = (JSONObject) obj.get(i);
			metadata1 = (JSONObject) meta.get("metadata");
			metadata2 = (JSONObject) metadata1.get("metadata");
			dimensione = (Long) metadata2.get("size");
			
			if (dimensione<minima)
			minima = dimensione;
			
			if (dimensione>massima)
			massima = dimensione;

			totale += dimensione;			

		}
		
		Long media = totale/obj.size();
		
		JSONStatistiche Json = new JSONStatistiche(minima,media,massima);
		
		return Json;
		
	}
	
public JSONStatistiche StatisticaDimensioneMeta(JSONArray obj) {
		
		JSONObject meta = new JSONObject();
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		Long dimensione = (long) 0;
		
		if (obj.isEmpty()) return null;
		


		meta = (JSONObject) obj.get(0);
		dimensione = (Long) meta.get("size");
		
    	Long minima = dimensione;
	    Long massima = dimensione;
	    Long totale = dimensione;
		
		for (int i=1;i<obj.size();i++) {
			meta = (JSONObject) obj.get(i);
			dimensione = (Long) meta.get("size");
			
			if (dimensione<minima)
			minima = dimensione;
			
			if (dimensione>massima)
			massima = dimensione;

			totale += dimensione;			

		}
		
		Long media = totale/obj.size();
		
		JSONStatistiche Json = new JSONStatistiche(minima,media,massima);
		
		return Json;
		
	}

}
