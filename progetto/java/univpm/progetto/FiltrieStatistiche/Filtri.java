package univpm.progetto.FiltrieStatistiche;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Filtri {
	
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

	public boolean FiltroParametri(int anno1, int anno2, int mese1, int mese2, int giorno1, int giorno2) {

		 
		Calendar calndr1
        = new GregorianCalendar(anno1, mese1, giorno1);
		 Calendar calndr2
        = new GregorianCalendar(anno2, mese2, giorno2);
		 
		 if (calndr1.compareTo(calndr2)<0) return false;
		
		return true;
	}

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
