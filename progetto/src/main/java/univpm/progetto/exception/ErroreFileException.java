package univpm.progetto.exception;

/**
 * Tale classe rappresenta un'eccezione personalizzata che estende Exception
 * @author Proietti Marco
 * @author Traini Davide
 *
 */


public class ErroreFileException extends Exception {

	private static final long serialVersionUID = 5L;

	public ErroreFileException() {
		super();
	}

	public ErroreFileException(String message) {
		super(message);
	}


}