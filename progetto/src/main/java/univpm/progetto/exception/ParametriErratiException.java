package univpm.progetto.exception;

/**
 * Tale classe rappresenta un'eccezione personalizzata che estende Exception
 * @author Proietti Marco
 * @author Traini Davide
 *
 */

public class ParametriErratiException extends Exception {

	private static final long serialVersionUID = 4L;

	public ParametriErratiException() {
		super();
	}

	public ParametriErratiException(String message) {
		super(message);
	}
	

}
