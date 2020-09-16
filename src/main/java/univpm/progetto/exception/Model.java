package univpm.progetto.exception;

/**
 * Model che rappresenta gli errori che sono restituiti al chiamante
 * 
 * @author Proietti Marco
 * @author Traini Davide
 */

public class Model {

	public String errore;

	public Model(String errore) {
		super();
		this.errore = errore;
	}

	public String getErrore() {
		return errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}

}
