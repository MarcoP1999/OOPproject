package univpm.progetto.elaborazione;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.progetto.Json.JSONparse;
import univpm.progetto.exception.ErroreFileException;
import univpm.progetto.exception.FailDataException;
import univpm.progetto.exception.FailDimException;
import univpm.progetto.exception.FormatoNonTrovatoException;
import univpm.progetto.exception.ParametriErratiException;
import univpm.progetto.filtri_e_statistiche.Filtri;
import univpm.progetto.verifiche.Verifica;


/**
 * Classe di elaborazione principale: contiene tutti i metodi che elaborano richieste sul 
 * dataset ottenuto con l'utilizzo dell'API "file-get_metadata" di DROPBOX
 * 
 * @author Proietti Marco
 * @author Davide Traini
 */


public class SearchperMeta {
	
	
	/**
	 * Chiamata quando si intende ricercare in base al tipo di file e alla dimensione
	 * @param string è una List di String che contiene: formato,operatore,dimensione
	 * @return JSONArray contenente gli elementi ricercati 
	 * @throws FormatoNonTrovatoException se il formato inserito non è supportato 
	 * @throws ParametriErratiException se i parametri sono stati inseriti in ordine errato
	 * o se il tipo inserito è diverso da quello richiesto 
	 * @throws FailDimException se il tipo inserito è diverso da quello richiesto
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 */
	
	
	public JSONArray TipoDiFile(List<String> string)
			throws FormatoNonTrovatoException, ParametriErratiException, FailDimException, ErroreFileException {

		Verifica verifica = new Verifica();
		Filtri filtro = new Filtri();
		Long in = (long) 0;
		String tipo = "";
		String op = "";
		try {
			tipo = string.get(0);
			op = string.get(1);
			in = Long.parseLong(string.get(2));
			// se il parsing non è riuscito (l'elemento inserito non è un intero)
			// restituisce un errore
		} catch (NumberFormatException e) {
			throw new FailDimException("inserire un parametro intero come terzo parametro");
		}

		// se l'operatore è diverso da quelli ammessi, restituisce un erroe al chiamante
		verifica.VerificaOperatore(op);
		// se il formato inserito non è compatibile con quelli gestiti restituisce un errore
		verifica.VerificaFormato(tipo);

		JSONArray finale = new JSONArray();

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

		return finale;

	}
	
	/**
	 * Chiamata quando si intende ricercare in base a nome o parte di esso
	 * @param body: una String che rappresenta il nome cercato (o parte iniziale del nome)
	 * @return JSONArray contenente gli elementi ricercati 
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 */
	

	public JSONArray NomeFile(String body) throws ErroreFileException {

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

			// entro se il nome del file in analisi ha lunghezza >= del nome cercato
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
	 * Chiamata quando si intende ricercare in base alla data di modifica
	 * @param body: List contenente le due date con cui filtrare i dati
	 * @return JSONArray contenente gli elementi modificati in data compresa tra le due in ingresso
	 * @throws FailDataException se la data inserita non rispetta il formato
	 * @throws ParametriErratiException se i parametri sono inseriti in ordine errato o se 
	 * non rispettano il tipo richiesto
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 */
	
	public JSONArray Data(List<String> body) throws FailDataException, ParametriErratiException, ErroreFileException {

		if (body.size() > 2) {
			throw new ParametriErratiException("inserire solamente 2 parametri");
		}

		String data1 = body.get(0);
		String data2 = body.get(1);
		int anno1, anno2, anno3, mese1, mese2, mese3, giorno1, giorno2, giorno3;

		// ritorna un errore se la data non rispetta il formato
		if (data1.length() != 10 || data2.length() != 10) {
			throw new FailDataException("inserire una data con formato giusto(0000-00-00)");
		}

		// vengono eliminati i separatori dalle stringhe creando delle stringhe più corte
		// che vengono subito sottoposte a parsing in modo da creare interi confrontabili
		try {
			anno1 = Integer.parseInt(data1.substring(0, 4));
			anno2 = Integer.parseInt(data2.substring(0, 4));
			mese1 = Integer.parseInt(data1.substring(5, 7));
			mese2 = Integer.parseInt(data2.substring(5, 7));
			giorno1 = Integer.parseInt(data1.substring(8, 10));
			giorno2 = Integer.parseInt(data2.substring(8, 10));
		} catch (NumberFormatException e) {
			throw new FailDataException("Scrivere una data corretta");
		}

		Verifica verifica = new Verifica();
		Filtri data = new Filtri();
		// verifica la correttezza delle date e le inizializza
		Calendar calndr1 = verifica.VerificaEsistenza(anno1, mese1, giorno1);
		Calendar calndr2 = verifica.VerificaEsistenza(anno2, mese2, giorno2);

		// restituisce errore se la prima data precede la seconda
		verifica.VerificaParametri(calndr1, calndr2);

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

				String st3 = mod.substring(0, 4);

				String str3 = mod.substring(5, 7);

				String stri3 = mod.substring(8, 10);

				try {
					anno3 = Integer.parseInt(st3);
					mese3 = Integer.parseInt(str3);
					giorno3 = Integer.parseInt(stri3);
				} catch (NumberFormatException e) {
					throw new NumberFormatException();
				}

				Calendar calndr3 = verifica.VerificaEsistenza(anno3, mese3, giorno3);
				// restituisce true solo se la data dell'elemento in esame è compresa tra le 
				// due inserite
				Boolean cont = data.FiltroData(calndr1, calndr2, calndr3);

				if (cont)
					finale.add(meta);
			}

		}
		return finale;
	}

	/**
	 * Chiamata quando si intende ricercare in base a tipo di file, dimensione, altezza 
	 * e larghezza dell'immagine
	 * @param string è una List di String che contiene: formato,operatore,altezza,larghezza
	 * @return JSONArray contenente gli elementi ricercati 
	 * @throws FormatoNonTrovatoException se il formato inserito non è supportato 
	 * @throws ParametriErratiException se i parametri sono stati inseriti in ordine errato
	 * o se il tipo inserito è diverso da quello richiesto 
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 */

	public JSONArray TipoDiFileParametri(List<String> string)
			throws FormatoNonTrovatoException, ParametriErratiException, ErroreFileException {

		Verifica verifica = new Verifica();
		Filtri filtro = new Filtri();
		Long alt = (long) 0;
		Long largh = (long) 0;
		Long dim = (long) 0;
		String tipo = "";
		String op1 = "";
		String op2 = "";
		try {
			tipo = string.get(0);
			op1 = string.get(1);
			alt = Long.parseLong(string.get(2));
			largh = Long.parseLong(string.get(3));
			op2 = string.get(4);
			dim = Long.parseLong(string.get(5));
			// se il parsing non è riuscito (gli elementi inseriti non sono interi)
			// restituisce un errore
		} catch (NumberFormatException e) {
			throw new ParametriErratiException("inserire un parametro intero come terzo,quarto e sesto parametro");
		}

		JSONArray finale = new JSONArray();
		// se l'operatore è diverso da quelli ammessi restituisce un erroe
		verifica.VerificaOperatore(op1);
		verifica.VerificaOperatore(op2);

		// se il formato inserito non è compatibile con quelli gestiti restituisce un
		// errore
		verifica.VerificaFormato(tipo);

		JSONObject meta = new JSONObject();
		JSONObject media_info = new JSONObject();
		JSONObject metadata = new JSONObject();
		JSONObject dimensioni = new JSONObject();
		String nome = "";
		Long size = (long) 0;
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
			size = (Long) meta.get("size");
			nome = (String) meta.get("name");
			media_info = (JSONObject) meta.get("media_info");
			if (media_info != null) {
				metadata = (JSONObject) media_info.get("metadata");
				dimensioni = (JSONObject) metadata.get("dimensions");
				altezza = (Long) dimensioni.get("height");
				larghezza = (Long) dimensioni.get("width");

				String fine = "";

				// tale metodo restituisce true solo se la dimensione rispetta le richieste
				Boolean flag1 = filtro.FiltroAltLargh(alt, largh, altezza, larghezza, op1);
				Boolean flag2 = filtro.FiltroDimensione(dim, size, op1);
				if (flag1 && flag2) {
					// non faccio nulla se il nome esaminato è più corto di quello cercato
					if (nome.length() > tipo.length()) {
						fine = nome.substring(nome.length() - tipo.length());
						if (fine.equals(tipo))
							finale.add(meta);

					}
				}
			}
		}

		return finale;

	}
}
