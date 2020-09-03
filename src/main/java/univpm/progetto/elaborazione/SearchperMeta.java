package univpm.progetto.elaborazione;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.progetto.FiltrieStatistiche.Filtri;
import univpm.progetto.Json.JSONparse;
import univpm.progetto.exception.FailDataException;
import univpm.progetto.exception.FailDimException;
import univpm.progetto.exception.FormatoNonTrovatoException;
import univpm.progetto.exception.ParametriErratiException;


/**
 * Classe di elaborazione principale: contiene tutti i metodi che elaborano richieste sul 
 * dataset ottenuto con l'utilizzo dell'API "file-get_metadata" di DROPBOX
 * 
 * @author Proietti Marco
 * @author Davide Traini
 */


public class SearchperMeta {
	
	/**
	 * 
	 * @param string è una List di String che contiene: formato,operatore,dimensione
	 * @return JSONArray contenente gli elementi ricercati 
	 * @throws FormatoNonTrovatoException se il formato inserito non è supportato 
	 * @throws ParametriErratiException se i parametri sono stati inseriti in ordine errato
	 * o se il tipo inserito è diverso da quello richiesto 
	 * @throws FailDimException se il tipo inserito è diverso da quello richiesto
	 */
	
	
	public JSONArray TipoDiFile(List<String> string) throws FormatoNonTrovatoException, ParametriErratiException, FailDimException {

		Long in = (long) 0;
		String tipo = "";
		String op = "";
		try {
			tipo = string.get(0);
			op = string.get(1);
			in = Long.parseLong(string.get(2));
		// se il parsing non è riuscito (l'elemento inserito non è un intero) restituisce un errore
		} catch (NumberFormatException e) {
			throw new FailDimException("inserire un parametro intero come terzo parametro");
		}
		// se l'operatore è diverso da quelli ammessi, restituisce un erroe al chiamante 
		if (!op.equals("<=") && !op.equals("<") && !op.equals("=") && !op.equals(">") && !op.equals(">=")) {
			throw new ParametriErratiException("inserire un operatore d'ordine come secondo parametro");
		}
		

		JSONArray finale = new JSONArray();
		// se il formato inserito non è compatibile con quelli gestiti restituisce un erroe
		if (tipo.contentEquals("jpg") || tipo.contentEquals("png") || tipo.contentEquals("tiff") || tipo.contentEquals("jpeg")) {

			JSONObject meta = new JSONObject();
			String nome = "";
			Long dimensione = (long) 0;
			// JSONParse è la classe che si occupa di scaricare il dataset grazie alle API
			JSONparse json2 = new JSONparse();
			JSONArray array = json2.returnMetadataJson();

			// tale riscrittura consentirà un più semplice controllo del tipo 
			if (tipo.contentEquals("jpg"))
				tipo = ".jpg";
			if (tipo.contentEquals("jpg"))
				tipo = ".jpeg";
			if (tipo.contentEquals("png"))
				tipo = ".png";
			if (tipo.contentEquals("tiff"))
				tipo = ".tiff";

			// in ogni cella è presente un json relativo ad un elemento da esaminare 
			for (int i = 0; i < array.size(); i++) {
				meta = (JSONObject) array.get(i);
				nome = (String) meta.get("name");
				dimensione = (Long) meta.get("size");

				String fine = "";
				
				Filtri filtro = new Filtri();
				// tale metodo restituisce true solo se la dimensione rispetta le richieste
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
					"inserire un formato supportato (jpg,png o tiff) come primo parametro");
		}

		return finale;

	}
	
	/**
	 * 
	 * @param body: una String che rappresenta il nome cercato (o parte iniziale del nome)
	 * @return JSONArray contenente gli elementi ricercati 
	 */
	

	public JSONArray NomeFile(String body) {
		

		String nomeinput = body.toLowerCase();

		JSONArray finale = new JSONArray();
		JSONObject meta = new JSONObject();
		String nome = "";
		// JSONParse è la classe che si occupa di scaricare il dataset grazie alle API 
		JSONparse json2 = new JSONparse();
		JSONArray array = json2.returnMetadataJson();

		// in ogni cella è presente un json relativo ad un elemento da esaminare 
		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			nome = (String) meta.get("name");
			String fine = "";

			String nomejson = nome.toLowerCase();

			int k = nomeinput.length();
			int j = nomejson.length();
			
			// se il nome del file in analisi ha lunghezza >= del nome cercato, allora entro
			if (j >= k) {
				// creo una stringa di dimensione k contenete solo gli ultimi caratteri
				fine = nomejson.substring(0, nomeinput.length());
				if (fine.equals(nomeinput)) {
					finale.add(meta);
			}
		}
		}

		return finale;
	}

	/**
	 * 
	 * @param body: List contenente le due date con cui filtrare i dati
	 * @return JSONArray contenente gli elementi modificati in data compresa tra le due in ingresso
	 * @throws FailDataException se la data inserita non rispetta il formato
	 * @throws ParametriErratiException se i parametri sono inseriti in ordine errato o se 
	 * non rispettano il tipo richiesto
	 */
	
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
		
		// ritorna un errore se la data non rispetta il formato
		if (data1.length()!=10 || data2.length()!=10) {
			throw new  FailDataException("inserire una data con formato giusto(0000-00-00)");
			}
		
		// vengono eliminati i separatori dalle stringhe creando delle stringhe più corte 
		// che vengono subito sottoposte a parsing in modo da creare interi confrontabili
		try {
			anno1 = Integer.parseInt(data1.substring(0,4));
			anno2 = Integer.parseInt(data2.substring(0,4));
			mese1 = Integer.parseInt(data1.substring(5,7));
			mese2 = Integer.parseInt(data2.substring(5,7));
			giorno1 = Integer.parseInt(data1.substring(8,10));
			giorno2 = Integer.parseInt(data2.substring(8,10));
		} catch (NumberFormatException e) {
			throw new FailDataException("Scrivere una data corretta");
		}
		
		// entra se la data è errata
		if (anno1 <= 0 || anno2 <= 0 || mese1 <= 0 || mese2 <= 0 || giorno1 <= 0 || giorno2 <= 0) {
			throw new FailDataException("Scrivere una data corretta");
		}
		if(mese1>12 || mese2>12 || giorno1>31 || giorno2>31) {
			throw new FailDataException("Scrivere una data corretta");
		}
		
		Filtri data = new Filtri();
		// restituisce errore se la prima data precede la seconda 
		if (!data.FiltroParametri(anno1, anno2, mese1, mese2, giorno1, giorno2)) {
			throw new FailDataException("inserire nuovamente i parametri in modo che il primo rappresenti la data maggiore ed il secondo quella inferiore");
		}

		JSONArray finale = new JSONArray();

		JSONObject meta = new JSONObject();
		String mod = "";
		// JSONParse è la classe che si occupa di scaricare il dataset grazie alle API
		JSONparse json2 = new JSONparse();
		JSONArray array = json2.returnMetadataJson();
		
		// in ogni cella è presente un json relativo ad un elemento da esaminare 
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
				
				// restituisce true solo se la data dell'elemento in esame è compresa tra le due inserite
				Boolean cont = data.FiltroData(anno1, anno2, anno3, mese1, mese2, mese3, giorno1, giorno2, giorno3);

				
				if (cont)
					finale.add(meta);
			}

		}
		return finale;
	}

	/**
	 * 
	 * @param string è una List di String che contiene: formato,operatore,altezza,larghezza
	 * @return JSONArray contenente gli elementi ricercati 
	 * @throws FormatoNonTrovatoException se il formato inserito non è supportato 
	 * @throws ParametriErratiException se i parametri sono stati inseriti in ordine errato
	 * o se il tipo inserito è diverso da quello richiesto 
	 */

	public JSONArray TipoDiFileParametri(List<String> string) throws FormatoNonTrovatoException, ParametriErratiException {

			Long alt = (long) 0;
			Long largh = (long) 0;
			String tipo = "";
			String op = "";
			try {
				tipo = string.get(0);
				op = string.get(1);
				alt = Long.parseLong(string.get(2));
				largh = Long.parseLong(string.get(3));
			// se il parsing non è riuscito (gli elementi inseriti non sono interi) restituisce un errore
			} catch (NumberFormatException e) {
				throw new ParametriErratiException("inserire un parametro intero come terzo e quarto parametro");
			}

			JSONArray finale = new JSONArray();
			// se l'operatore è diverso da quelli ammessi restituisce un erroe  
			if (!op.equals("<=") && !op.equals("<") && !op.equals("=") && !op.equals(">") && !op.equals(">=")) {
				throw new ParametriErratiException("inserire un operatore d'ordine come secondo parametro");
			}

			// se il formato inserito non è compatibile con quelli gestiti restituisce un erroe  
			if (tipo.contentEquals("jpg") || tipo.contentEquals("png") || tipo.contentEquals("tiff") || tipo.contentEquals("jpeg")) {

				JSONObject meta = new JSONObject();
				JSONObject media_info = new JSONObject();
				JSONObject metadata = new JSONObject();
				JSONObject dimensioni = new JSONObject();
				String nome = "";
				Long altezza = (long) 0;
				Long larghezza = (long) 0;
				// JSONParse è la classe che si occupa di scaricare il dataset grazie alle API
				JSONparse json2 = new JSONparse();
				JSONArray array = json2.returnMetadataJson();

				// tale riscrittura consentirà un più semplice controllo del tipo
				if (tipo.contentEquals("jpg"))
					tipo = ".jpg";
				if (tipo.contentEquals("jpg"))
					tipo = ".jpeg";
				if (tipo.contentEquals("png"))
					tipo = ".png";
				if (tipo.contentEquals("tiff"))
					tipo = ".tiff";

				// in ogni cella è presente un json relativo ad un elemento da esaminare 
				for (int i = 0; i < array.size(); i++) {
					meta = (JSONObject) array.get(i);
					nome = (String) meta.get("name");
					media_info = (JSONObject) meta.get("media_info");
					if (media_info!=null) {
					metadata = (JSONObject) media_info.get("metadata");
					dimensioni = (JSONObject) metadata.get("dimensions");
					altezza = (Long) dimensioni.get("height");
					larghezza = (Long) dimensioni.get("width");
					
					String fine = "";
					
					Filtri filtro = new Filtri();
					// tale metodo restituisce true solo se la dimensione rispetta le richieste
					Boolean dim = filtro.FiltroDimensione(alt,largh, altezza, larghezza, op);
					if (dim) {
						// non faccio nulla se il nome esaminato è più corto di quello cercato
						if (nome.length() > tipo.length()) {
							fine = nome.substring(nome.length() - tipo.length());
							if (fine.equals(tipo))
								finale.add(meta);

						}
					}
					}
				}
			} else {
				throw new FormatoNonTrovatoException(
						"inserire un formato supportato (jpg,png o tiff) come primo parametro");
			}

			return finale;

		}
}

