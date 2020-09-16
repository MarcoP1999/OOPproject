package univpm.progetto.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import univpm.progetto.Json.JSONFinale;

import univpm.progetto.elaborazione.Searchper;
import univpm.progetto.elaborazione.SearchperMeta;
import univpm.progetto.exception.ErroreFileException;
import univpm.progetto.exception.FailDataException;
import univpm.progetto.exception.FailDimException;
import univpm.progetto.exception.FormatoNonTrovatoException;
import univpm.progetto.exception.ParametriErratiException;
import univpm.progetto.filtri_e_statistiche.JSONStatistiche;
import univpm.progetto.filtri_e_statistiche.Statistiche;

/**
 * Tale classe gestisce le chiamate effettuate tramite Postman
 * 
 * @author Proietti Marco
 * @author Davide Traini
 *
 */

@RestController
public class SimpleRestController {

	/**
	 * Risponde alla richiesta (GET) /search_tipo_dim_altezzalarghezzaMeta
	 * 
	 * @param corpo è una List composta da 4 elementi inseriti come parametri tramite
	 *  postman. I quattro elementi rappresentano: 
	 *  1)Tipo formato da ricercare 
	 *  2)Operatore d'ordinamento che serve per filtrare in base ai 2 successivi parametri 
	 *  3)Altezza richiesta
	 *  4)Larghezza richiesta
	 *              
	 * @return JSONFinale comprendente elementi cercati e statistiche
	 * 
	 * @throws FormatoNonTrovatoException se il formato richiesto non è supportato
	 * @throws ParametriErratiException   se l'ordine di inserimento dei parametri è errato
	 *  o se essi non rispettano il tipo richiesto
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 */

	@GetMapping("/search_tipo_dim_altezzalarghezzaMeta")
	public JSONFinale alt_largh_Meta(@RequestParam List<String> corpo)
			throws FormatoNonTrovatoException, ParametriErratiException, ErroreFileException {
		SearchperMeta cerca = new SearchperMeta();
		JSONArray obj2 = cerca.TipoDiFileParametri(corpo);
		Statistiche stats = new Statistiche();
		JSONStatistiche statistiche = new JSONStatistiche();
		statistiche = stats.StatisticaDimensioneMeta(obj2);
		JSONFinale finale = new JSONFinale(obj2, statistiche);
		return finale;
	}

	/**
	 * Risponde alla richiesta (GET) /search_tipo_dimMeta
	 * 
	 * @param corpo è una List composta da 3 elementi inseriti come parametri tramite
	 * postman. I tre elementi rappresentano: 
	 * 1)Tipo formato da ricercare 
	 * 2)Operatore d'ordinamento che serve per filtrare in base alla dimensione richiesta 
	 * 3)Dimensione richiesta
	 *              
	 * @return JSONFinale comprendente elementi cercati e statistiche
	 * 
	 * @throws FormatoNonTrovatoException se il formato richiesto non è supportato
	 * @throws ParametriErratiException   se l'ordine di inserimento dei parametri è errato
	 *  o se essi non rispettano il tipo richiesto
	 * @throws FailDimException se il tipo inserito è diverso da quello richiesto
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 */

	@GetMapping("/search_tipo_dimMeta")
	public JSONFinale dim_Meta(@RequestParam List<String> corpo)
			throws FormatoNonTrovatoException, ParametriErratiException, FailDimException, ErroreFileException {
		SearchperMeta cerca = new SearchperMeta();
		JSONArray obj2 = cerca.TipoDiFile(corpo);
		Statistiche stats = new Statistiche();
		JSONStatistiche statistiche = new JSONStatistiche();
		statistiche = stats.StatisticaDimensioneMeta(obj2);
		JSONFinale finale = new JSONFinale(obj2, statistiche);
		return finale;
	}
	
	/**
	 * Risponde alla richiesta (GET) /search_nomeMeta
	 * 
	 * @param corpo è una String composta un elemento inserito come parametro
	 * tramite postman, esso rappresenta il nome, o i primi caratteri del nome di un file 
	 * ricercato
	 *              
	 * @return JSONArray comprendente elementi cercati 
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 * 
	 */

	@GetMapping("/search_nomeMeta")
	public JSONArray nome_Meta(@RequestParam String corpo) throws ErroreFileException {
		SearchperMeta cerca = new SearchperMeta();
		JSONArray obj2 = cerca.NomeFile(corpo);
		return obj2;
	}
	
	/**
	 * Risponde alla richiesta (GET) /search_dataMeta
	 * 
	 * @param corpo è una List composta da 2 elementi inseriti come parametri
	 * tramite postman. I due elementi rappresentano: 
	 * 1)Data1 
	 * 2)Data2 (in modo che sia < di Data1)
	 *              
	 * @return JSONArray comprendente elementi cercati
	 * 
	 * @throws FailDataException se la data è scritta con formato errato
	 * @throws ParametriErratiException se l'ordine di inserimento dei parametri è errato
	 *  o se essi non rispettano il tipo richiesto
	 * @throws ErroreFileException se il file di testo è scritto in maniera errata
	 */

	@GetMapping("/search_dataMeta")
	public JSONArray data_Meta(@RequestParam List<String> corpo) throws FailDataException, ParametriErratiException, ErroreFileException {
		SearchperMeta cerca = new SearchperMeta();
		JSONArray obj2 = cerca.Data(corpo);
		return obj2;
	}
	
	
	
	/**
	 * Risponde alla richiesta (GET) /search_tipo_dim
	 * 
	 * @param corpo è una List composta da 3 elementi inseriti come parametri tramite
	 * postman. I tre elementi rappresentano: 
	 * 1)Tipo formato da ricercare 
	 * 2)Operatore d'ordinamento che serve per filtrare in base alla dimensione richiesta 
	 * 3)Dimensione richiesta
	 *              
	 * @return JSONFinale comprendente elementi cercati e statistiche
	 * 
	 * @throws FormatoNonTrovatoException se il formato richiesto non è supportato
	 * @throws ParametriErratiException   se l'ordine di inserimento dei parametri è errato
	 *  o se essi non rispettano il tipo richiesto
	 * @throws FailDimException se il tipo inserito è diverso da quello richiesto
	 */

	@GetMapping("/search_tipo_dim")
	public JSONFinale dim(@RequestParam List<String> corpo)
			throws FormatoNonTrovatoException, ParametriErratiException, FailDimException{
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.TipoDiFile(corpo);
		Statistiche stats = new Statistiche();
		JSONStatistiche statistiche = new JSONStatistiche();
		statistiche = stats.StatisticaDimensione(obj2);
		JSONFinale finale = new JSONFinale(obj2, statistiche);
		return finale;
	}
	
	/**
	 * Risponde alla richiesta (GET) /search_nome
	 * 
	 * @param corpo è una String composta un elemento inserito come parametro
	 * tramite postman, esso rappresenta il nome, o i primi caratteri del nome di un file 
	 * ricercato
	 *              
	 * @return JSONArray comprendente elementi cercati  
	 */

	@GetMapping("/search_nome")
	public JSONArray nome(@RequestParam String corpo) {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.NomeFile(corpo);
		return obj2;
	}

	
	/**
	 * Risponde alla richiesta (GET) /search_data
	 * 
	 * @param corpo è una List composta da 2 elementi inseriti come parametri
	 * tramite postman. I due elementi rappresentano: 
	 * 1)Data1 
	 * 2)Data2 (in modo che sia < di Data1)
	 *              
	 * @return JSONArray comprendente elementi cercati
	 * 
	 * @throws FailDataException se la data è scritta con formato errato
	 * @throws ParametriErratiException se l'ordine di inserimento dei parametri è errato
	 *  o se essi non rispettano il tipo richiesto
	 */
	
	@GetMapping("/search_data")
	public JSONArray data(@RequestParam List<String> corpo) throws FailDataException, ParametriErratiException {
		Searchper cerca = new Searchper();
		JSONArray obj2 = cerca.Data(corpo);
		return obj2;
	}

}
