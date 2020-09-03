package univpm.progetto.FiltrieStatistiche;

/**
 * Classe che rappresenta il model che verrà restituito come JSON al Client e che contiene le statistiche 
 * @author Proietti Marco
 * @author Traini Davide
 *
 */
public class JSONStatistiche {
	
	public Long minima;
	public Double media_matematica;
	public Double media_geometrica;
	public Long massima;
	
	
	public JSONStatistiche() {
		super();
	}


	public JSONStatistiche(Long minima, Double media_matematica, Double media_geometrica, Long massima) {
		super();
		this.minima = minima;
		this.media_matematica = media_matematica;
		this.media_geometrica = media_geometrica;
		this.massima = massima;
	}
	
	

}
