package univpm.progetto.elaborazione;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.progetto.FiltrieStatistiche.Filtri;
import univpm.progetto.Json.JSONparse;
import univpm.progetto.exception.FailDataException;
import univpm.progetto.exception.FormatoNonTrovatoException;
import univpm.progetto.exception.ParametriErratiException;

public class SearchperMeta {
	
	
	public JSONArray TipoDiFile(String tipo, String inp, String op) throws FormatoNonTrovatoException, ParametriErratiException {

		Long in = (long) 0;
		try {
			in = Long.parseLong(inp);
		} catch (NumberFormatException e) {
			throw new ParametriErratiException("inserire un parametro intero");
		}
		
		if (!op.equals("<=") && !op.equals("<") && !op.equals("=") && !op.equals(">") && !op.equals(">=")) {
			throw new ParametriErratiException("inserire un operatore d'ordine come secondo parametro");
		}
		

		JSONArray finale = new JSONArray();

		if (tipo.contentEquals("jpg") || tipo.contentEquals("png") || tipo.contentEquals("tiff") || tipo.contentEquals("jpeg")) {

			JSONObject meta = new JSONObject();
			String nome = "";
			Long dimensione = (long) 0;
			JSONparse json2 = new JSONparse();
			JSONArray array = json2.returnMetadataJson();

			if (tipo.contentEquals("jpg"))
				tipo = ".jpg";
			if (tipo.contentEquals("jpg"))
				tipo = ".jpeg";
			if (tipo.contentEquals("png"))
				tipo = ".png";
			if (tipo.contentEquals("tiff"))
				tipo = ".tiff";

			for (int i = 0; i < array.size(); i++) {
				meta = (JSONObject) array.get(i);
				nome = (String) meta.get("name");
				dimensione = (Long) meta.get("size");

				String fine = "";
				
				Filtri filtro = new Filtri();
				Boolean dim = filtro.FiltroDimensione(in, dimensione, op);
				if (dim) {
					if (nome.length() > tipo.length()) {
						fine = nome.substring(nome.length() - tipo.length());
						if (fine.equals(tipo))
							finale.add(meta);

					}
				}

			}
		} else {
			throw new FormatoNonTrovatoException(
					"inserire un formato supportato(jpg,png o tiff)");
		}

		return finale;

	}
	

	public JSONArray NomeFile(String body) {
		

		String nomeinput = body.toLowerCase();

		JSONArray finale = new JSONArray();
		JSONObject meta = new JSONObject();
		String nome = "";
		JSONparse json2 = new JSONparse();
		JSONArray array = json2.returnMetadataJson();


		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			nome = (String) meta.get("name");
			String fine = "";

			String nomejson = nome.toLowerCase();

			int k = nomeinput.length();
			int j = nomejson.length();
			
			
			if (j >= k) {
				fine = nomejson.substring(0, nomeinput.length());
				if (fine.equals(nomeinput)) {
					finale.add(meta);
			}
		}
		}

		return finale;
	}

	public JSONArray Data(List<String> body) throws FailDataException, ParametriErratiException {

		if (body.size() > 2) {
			throw new ParametriErratiException("inserire solamente 2 parametri");
		}

		String data1 = body.get(0);
		String data2 = body.get(1);
		int anno1;
		int anno2;
		int anno3;
		int mese1;
		int mese2;
		int mese3;
		int giorno1;
		int giorno2;
		int giorno3;
		
		if (data1.length()!=10 || data2.length()!=10) {
			throw new  FailDataException("inserire una data con formato giusto(0000-00-00)");
			}
		
		String st1 = data1.substring(0,4);
		String st2 = data2.substring(0,4);		
		String str1 = data1.substring(5,7);
		String str2 = data2.substring(5,7);
		String stri1 = data1.substring(8,10);
		String stri2 = data2.substring(8,10);


		try {
			anno1 = Integer.parseInt(st1);
			anno2 = Integer.parseInt(st2);
			mese1 = Integer.parseInt(str1);
			mese2 = Integer.parseInt(str2);
			giorno1 = Integer.parseInt(stri1);
			giorno2 = Integer.parseInt(stri2);
		} catch (NumberFormatException e) {
			throw new FailDataException("Scrivere una data corretta");
		}

		if (anno1 <= 0 || anno2 <= 0 || mese1 <= 0 || mese2 <= 0 || giorno1 <= 0 || giorno2 <= 0) {
			throw new FailDataException("Scrivere una data corretta");
		}
		if(mese1>12 || mese2>12 || giorno1>31 || giorno2>31) {
			throw new FailDataException("Scrivere una data corretta");
		}
		
		Filtri data = new Filtri();
		
		if (!data.FiltroParametri(anno1, anno2, mese1, mese2, giorno1, giorno2)) {
			throw new FailDataException("inserire nuovamente i parametri in modo che il primo rappresenti la data maggiore ed il secondo quella inferiore");
		}

		JSONArray finale = new JSONArray();

		JSONObject meta = new JSONObject();
		String mod = "";
		JSONparse json2 = new JSONparse();
		JSONArray array = json2.returnMetadataJson();

		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			mod = (String) meta.get("client_modified");



			if (mod != null) {
				
				String	st3 = mod.substring(0,4);
				
				String	str3 = mod.substring(5,7);

				String	stri3 = mod.substring(8,10);

				try {
					anno3 = Integer.parseInt(st3);
					mese3 = Integer.parseInt(str3);
					giorno3 = Integer.parseInt(stri3);
				} catch (NumberFormatException e) {
					throw new NumberFormatException();
				}
				
				
				Boolean cont = data.FiltroData(anno1, anno2, anno3, mese1, mese2, mese3, giorno1, giorno2, giorno3);

				
				if (cont)
					finale.add(meta);
			}

		}
		return finale;
	}


	public JSONArray TipoDiFileParametri(String tipo, String h, String w, String operatore) throws FormatoNonTrovatoException, ParametriErratiException {

			Long alt = (long) 0;
			Long largh = (long) 0;

			try {
				alt = Long.parseLong(h);
				largh = Long.parseLong(w);
			} catch (NumberFormatException e) {
				throw new ParametriErratiException("inserire un parametro intero");
			}

			JSONArray finale = new JSONArray();

			if (tipo.contentEquals("jpg") || tipo.contentEquals("png") || tipo.contentEquals("tiff") || tipo.contentEquals("jpeg")) {

				JSONObject meta = new JSONObject();
				JSONObject media_info = new JSONObject();
				JSONObject metadata = new JSONObject();
				JSONObject dimensioni = new JSONObject();
				String nome = "";
				Long altezza = (long) 0;
				Long larghezza = (long) 0;
				JSONparse json2 = new JSONparse();
				JSONArray array = json2.returnMetadataJson();

				if (tipo.contentEquals("jpg"))
					tipo = ".jpg";
				if (tipo.contentEquals("jpg"))
					tipo = ".jpeg";
				if (tipo.contentEquals("png"))
					tipo = ".png";
				if (tipo.contentEquals("tiff"))
					tipo = ".tiff";

				for (int i = 0; i < array.size(); i++) {
					meta = (JSONObject) array.get(i);
					nome = (String) meta.get("name");
					media_info = (JSONObject) meta.get("media_info");
					metadata = (JSONObject) media_info.get("metadata");
					dimensioni = (JSONObject) metadata.get("dimensions");
					altezza = (Long) dimensioni.get("height");
					larghezza = (Long) dimensioni.get("width");
					
					String fine = "";
					
					Filtri filtro = new Filtri();
					Boolean dim = filtro.FiltroDimensione(alt,largh, altezza, larghezza, operatore);
					if (dim) {
						if (nome.length() > tipo.length()) {
							fine = nome.substring(nome.length() - tipo.length());
							if (fine.equals(tipo))
								finale.add(meta);

						}
					}

				}
			} else {
				throw new FormatoNonTrovatoException(
						"inserire un formato supportato(jpg,png o tiff)");
			}

			return finale;

		}
}

