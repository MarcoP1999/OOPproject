package univpm.progetto.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Tale classe gestisce le varie tipologie di eccezione 
 * @author Proietti Marco
 * @author Traini Davide
 *
 */

@ControllerAdvice
public class ControllerErrori {
	
	
/**
 * Chiamato quando si deve gestire l'eccezione FailDataException
 * @param e è il tipo di eccezione lanciata
 * @return errore restituisce un messaggio di errore personalizzato
 */
	
	@ExceptionHandler(FailDataException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Model handleFailDataException(FailDataException e) {
		Model errore = new Model(e.getMessage());
		return errore;
	}
	
	
	/**
	 * Chiamato quando si deve gestire l'eccezione FailDimException
	 * @param e è il tipo di eccezione lanciata
	 * @return errore restituisce un messaggio di errore personalizzato
	 */
	
	@ExceptionHandler(FailDimException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Model handleFailDimException(FailDimException e) {
		Model errore = new Model(e.getMessage());
		return errore;
		
	}
	
	/**
	 * Chiamato quando si deve gestire l'eccezione FormatoNonTrovatoException
	 * @param e è il tipo di eccezione lanciata
	 * @return errore restituisce un messaggio di errore personalizzato
	 */
	
	@ExceptionHandler(FormatoNonTrovatoException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Model handleFormatoNonTrovatoException(FormatoNonTrovatoException e) {
		Model errore = new Model(e.getMessage());
		return errore;
		
	}
	
	/**
	 * Chiamato quando si deve gestire l'eccezione ParametriErratiException
	 * @param e è il tipo di eccezione lanciata
	 * @return errore restituisce un messaggio di errore personalizzato
	 */
	
	@ExceptionHandler(ParametriErratiException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Model handlerParametriErratiException(ParametriErratiException e) {
		Model errore = new Model(e.getMessage());
		return errore;
	}
	

}
