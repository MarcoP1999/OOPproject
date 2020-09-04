package univpm.progetto.exception;

/**
 * Tale classe rappresenta un'eccezione personalizzata che estende Exception
 * @author Proietti Marco
 * @author Traini Davide
 *
 */

public class FormatoNonTrovatoException extends Exception {

	private static final long serialVersionUID = 3L;

	public FormatoNonTrovatoException() {
		super();
	}

	public FormatoNonTrovatoException(String message) {
		super(message);
	}
	

}
