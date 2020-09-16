package univpm.progetto.filtri_e_statistiche;

/**
 * Classe che rappresenta il model che verrà restituito come JSON al Client e che contiene le statistiche 
 *
 * @author Proietti Marco
 * @author Traini Davide
 *
 */

public class JSONStatistiche {
	
	public Long minima;
	public Double media_aritmetica;
	public Double media_geometrica;
	public Long massima;
	
	
	public JSONStatistiche() {
		super();
	}


	public JSONStatistiche(Long minima, Double media_aritmetica, Double media_geometrica, Long massima) {
		super();
		this.minima = minima;
		this.media_aritmetica = media_aritmetica;
		this.media_geometrica = media_geometrica;
		this.massima = massima;
	}
	
	

}
