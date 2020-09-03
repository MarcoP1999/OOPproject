package univpm.progetto.exception;

/**
 * Tale classe rappresenta un'eccezione personalizzata che estende Exception
 * @author Proietti Marco
 * @author Traini Davide
 *
 */

public class FailDimException extends Exception {

	private static final long serialVersionUID = 2L;

	public FailDimException() {
		super();	
	}

	public FailDimException(String message) {
		super(message);
	}
	
		

}
