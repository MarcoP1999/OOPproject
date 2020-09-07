package univpm.progetto.FiltrieStatistiche;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe che contiene i metodi per filtrare le ricerche 
 * 
 * @author Proietti Marco
 * @author Davide Traini
 */


public class Filtri {
	
	/**
	 * Metodo per verificare se un elemento dell'array ha dimensione che rispetta le richieste
	 * 
	 * @param in Long dimensione inserita dall'utente 
	 * @param size Long dimensione dell'elemento i-esimo dell'array che stiamo analizzando
	 * @param operatore String inserito dall'utente
	 * @return boolean che sta ad indicare se l'elemento ripetta o meno la dimensione voluta
	 */
	public Boolean FiltroDimensione(Long in, Long size, String operatore) {

		if (size == null) {
			size = (long) 0;
		}

		if ((long) size < (long) in && operatore.equals("<")) {
			return true;
		}

		if ((long) size <= (long) in && operatore.equals("<=")) {
			return true;
		}

		if ((long) size == (long) in && operatore.equals("=")) {
			return true;
		}

		if ((long) size > (long) in && operatore.equals(">")) {
			return true;
		}

		if ((long) size >= (long) in && operatore.equals(">=")) {
			return true;
		}

		return false;

	}

	
	/**
	 * Metodo per verificare che l'elemento in analisi sia stato modificato nell'intervallo di date giusto
	 * @param c1 è la prima data inserita
	 * @param c2 è la seconda data inserita
	 * @param c3 è la data relativa all'elemento che si sta considerando nel ciclo 
	 *  
	 * @return boolean che indica se la stringa rispetta o meno le condizioni 
	 */
	public Boolean FiltroData(Calendar c1, Calendar c2, Calendar c3) {
		
		
		if(c1.compareTo(c3)>=0 && c3.compareTo(c2)>=0) return true;
		
		return false;
	}

	
	/***
	 * Metodo per verificare che le date siano inserite in ordine giusto(prima la maggiore)
	 * 
	 * @param c1 è la prima data inserita
	 * @param c2 è la seconda data inserita
	 * 
	 * @return Boolean che indica se le date sono state inserite in ordine giusto
	 */
	public boolean FiltroParametri(Calendar c1, Calendar c2) {
		 
		 if (c1.compareTo(c2)<0) return false;
		
		return true;
	}

	/**
	 * Metodo per verificare se un elemento dell'array ha altezza e larghezza che rispettano le richieste
	 * 
	 * @param alt Long altezza inserita dall'utente 
	 * @param largh Long larghezza inserita dall'utente
	 * @param altObj Long altezza dell'elemento i-esimo dell'array che stiamo analizzando
	 * @param larghObj Long larghezza dell'elemento i-esimo dell'array che stiamo analizzando
	 * @param operatore String inserito dall'utente
	 * @return boolean che sta ad indicare se l'elemento rispetta altezza e laghezza richieste 
	 */
	
	public Boolean FiltroDimensione(Long alt, Long largh, Long altObj, Long larghObj, String operatore) {
		
		if (altObj == null) {
			altObj = (long) 0;
		}
		
		if (larghObj == null) {
			larghObj = (long) 0;
		}

		if ((long) altObj < (long) alt && (long) larghObj < (long) alt && operatore.equals("<")) {
			return true;
		}

		if ((long) altObj <= (long) alt && (long) larghObj <= (long) alt && operatore.equals("<=")) {
			return true;
		}

		if ((long) altObj == (long) alt && (long) larghObj == (long) alt && operatore.equals("=")) {
			return true;
		}

		if ((long) altObj > (long) alt && (long) larghObj > (long) alt && operatore.equals(">")) {
			return true;
		}

		if ((long) altObj >= (long) alt && (long) larghObj >= (long) alt &&operatore.equals(">=")) {
			return true;
		}

		return false;
		
	}

}