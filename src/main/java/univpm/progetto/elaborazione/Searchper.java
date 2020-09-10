package univpm.progetto.elaborazione;

import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import univpm.progetto.FiltrieStatistiche.Filtri;
import univpm.progetto.Json.JSONparse;
import univpm.progetto.exception.ErroreFileException;
import univpm.progetto.exception.FailDataException;
import univpm.progetto.exception.FailDimException;
import univpm.progetto.exception.FormatoNonTrovatoException;
import univpm.progetto.exception.ParametriErratiException;
import univpm.progetto.verifiche.Verifica;


/**
 * Classe di elaborazione principale: contiene tutti i metodi che elaborano richieste sul 
 * dataset ottenuto con l'utilizzo dell'API "file-search" di DROPBOX
 * 
 * @author Proietti Marco
 * @author Davide Traini
 */

public class Searchper {

	/**
	 * Chiamata quando si intende ricercare in base a formato e dimensione
	 * @param string è una List di String che contiene: formato,operatore,dimensione
	 * @return JSONArray contenente gli elementi ricercati 
	 * @throws FormatoNonTrovatoException se il formato inserito non è supportato 
	 * @throws ParametriErratiException se i parametri sono stati inseriti in ordine errato
	 * @throws FailDimException se il tipo inserito è diverso da quello richiesto
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 */

	public JSONArray TipoDiFile(List<String> string) throws FormatoNonTrovatoException, ParametriErratiException, FailDimException, ErroreFileException {
		
		Verifica verifica = new Verifica();
		Filtri filtro = new Filtri();
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
		
		// se l'operatore è diverso da quelli ammessi restituisce un errore  
		verifica.VerificaOperatore(op);
		// se il formato inserito non è compatibile con quelli gestiti restituisce un erroe  
		verifica.VerificaFormato(tipo);

		JSONArray finale = new JSONArray();
		JSONObject meta = new JSONObject();
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		String nome = "";
		Long dimensione = (long) 0;
		// JSONParse è la classe che si occupa di scaricare il dataset grazie alle API 
		JSONparse json2 = new JSONparse();
		JSONObject obj2 = json2.returnSearchJson();
			
		// tale riscrittura consentirà un più semplice controllo del tipo 
		if (tipo.contentEquals("jpg"))
			tipo = ".jpg";
		if (tipo.contentEquals("jpg"))
			tipo = ".jpeg";
		if (tipo.contentEquals("png"))
			tipo = ".png";
		if (tipo.contentEquals("tiff"))
			tipo = ".tiff";

		JSONArray array = (JSONArray) obj2.get("matches");
			
		// in ogni cella è presente un json relativo ad un elemento da esaminare 
		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			metadata1 = (JSONObject) meta.get("metadata");
			metadata2 = (JSONObject) metadata1.get("metadata");
			nome = (String) metadata2.get("name");
			dimensione = (Long) metadata2.get("size");
			String fine = "";
				
			// tale metodo restituisce true solo se la dimensione rispetta le richieste
			Boolean dim = filtro.FiltroDimensione(in, dimensione, op);
			if (dim) {
				// non faccio nulla se il nome esaminato è più corto di quello cercato
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
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		String str = "";
		// JSONParse è la classe che si occupa di scaricare il dataset grazie alle API 
		JSONparse json2 = new JSONparse();
		JSONObject obj2 = json2.returnSearchJson();
		String fine = "";

		JSONArray array = (JSONArray) obj2.get("matches");

		// in ogni cella è presente un json relativo ad un elemento da esaminare 
		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			metadata1 = (JSONObject) meta.get("metadata");
			metadata2 = (JSONObject) metadata1.get("metadata");
			str = (String) metadata2.get("name");
			String nomejson = str.toLowerCase();

			int k = nomeinput.length();
			int j = nomejson.length();
			// se il nome del file in analisi ha lunghezza >= del nome cercato, allora entro
			if (j >= k) {
				// creo una stringa di dimensione k contenete solo gli ultimi caratteri
				fine = nomejson.substring(0, k);
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
		int anno1,anno2,anno3,mese1,mese2,mese3,giorno1,giorno2,giorno3;
		
		
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
		
		Verifica verifica = new Verifica();
		Filtri filtro = new Filtri();
		// verifica la correttezza delle date e le inizializza
		Calendar calndr1
        = verifica.VerificaEsistenza(anno1,mese1,giorno1);
		 Calendar calndr2
        = verifica.VerificaEsistenza(anno2,mese2,giorno2);;
		 
		
		// restituisce errore se la prima data precede la seconda 
		verifica.VerificaParametri(calndr1,calndr2);
	

		JSONArray finale = new JSONArray();

		JSONObject meta = new JSONObject();
		JSONObject metadata1 = new JSONObject();
		JSONObject metadata2 = new JSONObject();
		String mod = "";
		// JSONParse è la classe che si occupa di scaricare il dataset grazie alle API
		JSONparse json2 = new JSONparse();
		JSONObject obj2 = json2.returnSearchJson();

		JSONArray array = (JSONArray) obj2.get("matches");
		
		// in ogni cella è presente un json relativo ad un elemento da esaminare 
		for (int i = 0; i < array.size(); i++) {
			meta = (JSONObject) array.get(i);
			metadata1 = (JSONObject) meta.get("metadata");
			metadata2 = (JSONObject) metadata1.get("metadata");
			mod = (String) metadata2.get("client_modified");


			
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
				// verifica l'esistenza della data e restituisce la data come calendar
				 Calendar calndr3
			        = verifica.VerificaEsistenza(anno3, mese3, giorno3);
				// restituisce true solo se la data dell'elemento in esame è compresa tra le due inserite
				Boolean cont = filtro.FiltroData(calndr1,calndr2,calndr3);
				
				if (cont)
					finale.add(meta);
			}

		}
		return finale;
	}

}
