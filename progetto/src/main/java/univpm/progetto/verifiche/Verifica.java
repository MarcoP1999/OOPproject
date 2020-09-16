package univpm.progetto.verifiche;

import java.util.Calendar;

import univpm.progetto.exception.FailDataException;
import univpm.progetto.exception.FormatoNonTrovatoException;
import univpm.progetto.exception.ParametriErratiException;

/**
 * Classe che contiene i metodi per verificare alcune proprietà degli elementi in esame
 * @author Proietti Marco
 * @author Traini Davide
 */

public class Verifica {

	
	 /**
	 * Metodo per verificare che le date inserite siano realmente esistenti 
	 * e per inizializzarle
	 * 
	 * @param a anno 
	 * @param m mese 
	 * @param g giorno
	 * 
	 * @return c1 data sotto forma di Calendar
	 * @throws FailDataException se le date non sono reali
	 */
	
	public Calendar VerificaEsistenza(int a,int m,int g) throws FailDataException {
		 
		
		Calendar c1 = Calendar.getInstance();
		c1.setLenient(false);
		// la classe considera i mesi partendo da 00 e non da 01 
		c1.set(a, m-1, g);
		try {
			c1.getTime();
		}
		
		catch (Exception e) {
		throw new FailDataException("Inserire una data corretta");
		}
		
		return c1;
	}
	
	
	
	/**
	 *  Metodo per verificare che l'operatore sia supportato
	 * 
	 * @param op operatore inserito dall'utente
	 * @return true se l'operatore è ammesso
	 * @throws ParametriErratiException se l'operatore non è ammesso
	 */
	
	public Boolean VerificaOperatore(String op) throws ParametriErratiException {
		
		if (!op.equals("<=") && !op.equals("<") && !op.equals("=") && !op.equals(">") && !op.equals(">=")) {
			throw new ParametriErratiException("inserire un operatore d'ordine come secondo parametro");
		}
		return true;
		
	}
	
	
	
	/**
	 *  Metodo per verificare che il formato sia supportato
	 * 
	 * @param tipo è il formato richiesto dall'utente
	 * @return true se il formato è supportato
	 * @throws FormatoNonTrovatoException se il formato non è supportato
	 */
	
	public Boolean VerificaFormato(String tipo) throws FormatoNonTrovatoException {
		
		if (!tipo.contentEquals("jpg") && !tipo.contentEquals("png") && !tipo.contentEquals("tiff") && !tipo.contentEquals("jpeg")) {
			throw new FormatoNonTrovatoException("inserire un formato supportato (jpg, png, jpeg o tiff) come primo parametro");
		}
			return true;
	}
	
	/***
	 * Metodo per verificare che le date siano inserite in ordine giusto(prima la maggiore)
	 * 
	 * @param c1 è la prima data inserita
	 * @param c2 è la seconda data inserita
	 * 
	 * @return Boolean che indica se le date sono state inserite in ordine giusto
	 * @throws ParametriErratiException se i parametri sono inseriti in ordine errato o se 
	 * non rispettano il tipo richiesto
	 */
	
	public Boolean VerificaParametri(Calendar c1, Calendar c2) throws ParametriErratiException {
		 
		 if (c1.compareTo(c2)<0) {
		throw new ParametriErratiException("inserire nuovamente i parametri in modo che il primo rappresenti una data successiva rispetto alla seconda");
		 }
		 
		
		return true;
	}

	
	
	
	
}
