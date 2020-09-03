package univpm.progetto.exception;

/**
 * Tale classe rappresenta un'eccezione personalizzata che estende Exception
 * @author Proietti Marco
 * @author Traini Davide
 *
 */


public class FailDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public FailDataException() {
		super();
	}

	public FailDataException(String message) {
		super(message);
	}


}
