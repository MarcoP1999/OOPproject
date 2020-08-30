package univpm.progetto.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ControllerErrori {
	
	@ExceptionHandler(FailDataException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Model handleFailDataException(FailDataException e) {
		Model errore = new Model(e.getMessage());
		return errore;
	}
	
	
	@ExceptionHandler(FailDimException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Model handleFailDimException(FailDimException e) {
		Model errore = new Model(e.getMessage());
		return errore;
		
	}
	
	
	@ExceptionHandler(FormatoNonTrovatoException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Model handleFormatoNonTrovatoException(FormatoNonTrovatoException e) {
		Model errore = new Model(e.getMessage());
		return errore;
		
	}
	
	@ExceptionHandler(ParametriErratiException.class)
	@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public Model handlerParametriErratiException(ParametriErratiException e) {
		Model errore = new Model(e.getMessage());
		return errore;
	}
	

}
