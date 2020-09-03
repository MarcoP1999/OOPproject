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
	 * 
	 * @param anno1 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param anno2 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param anno3 intero ottenuto dal parsing di parte della stringa "modified" relativa all'elemento in analisi 
	 * @param mese1 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param mese2 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param mese3 intero ottenuto dal parsing di parte della stringa "modified" relativa all'elemento in analisi 
	 * @param giorno1 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param giorno2 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param giorno3 intero ottenuto dal parsing di parte della stringa "modified" relativa all'elemento in analisi 
	 * @return boolean che indica se la stringa rispetta o meno le condizioni 
	 */
	public Boolean FiltroData(int anno1, int anno2, int anno3, int mese1, int mese2, int mese3, int giorno1,
			int giorno2, int giorno3) {
		
		Boolean cont = false;
		
		 Calendar calndr1
         = new GregorianCalendar(anno1, mese1, giorno1);
		 Calendar calndr2
         = new GregorianCalendar(anno2, mese2, giorno2);
		 Calendar calndr3
         = new GregorianCalendar(anno3, mese3, giorno3);
		
		if(calndr1.compareTo(calndr3)>=0 && calndr3.compareTo(calndr2)>=0) cont = true;
		
		return cont;
	}

	
	/***
	 * Metodo per verificare che le date siano inserite in ordine giusto 
	 * 
	 * @param anno1 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param anno2 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param mese1 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param mese2 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param giorno1 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @param giorno2 intero ottenuto dal parsing di parte della stringa inserita dall'utente 
	 * @return
	 */
	public boolean FiltroParametri(int anno1, int anno2, int mese1, int mese2, int giorno1, int giorno2) {

		 
		Calendar calndr1
        = new GregorianCalendar(anno1, mese1, giorno1);
		 Calendar calndr2
        = new GregorianCalendar(anno2, mese2, giorno2);
		 
		 if (calndr1.compareTo(calndr2)<0) return false;
		
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
