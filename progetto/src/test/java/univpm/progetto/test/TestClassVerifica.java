package univpm.progetto.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import univpm.progetto.exception.FailDataException;
import univpm.progetto.exception.FormatoNonTrovatoException;
import univpm.progetto.exception.ParametriErratiException;
import univpm.progetto.verifiche.Verifica;

class TestClassVerifica {
	
	private Verifica verifica = new Verifica();	
	private Calendar calendario1 = Calendar.getInstance();
	private Calendar calendario2 = Calendar.getInstance();

	@BeforeEach
	void setup() throws Exception {
		calendario1.set(2020,9-1,11);
		calendario2.set(2020,10-1,8);
	}
	
	@Test
	void operatore() throws ParametriErratiException  {
		assertEquals(true,verifica.VerificaOperatore(">"));
	}
	
	@Test
	void operatore_exc() {
		assertThrows(ParametriErratiException.class,()->verifica.VerificaOperatore("ciao"));
	}
	
	@Test
	void data_exc() {
		assertThrows(FailDataException.class,()->verifica.VerificaEsistenza(2020,00,11));
	}
	
	@Test
	void formato() throws FormatoNonTrovatoException  {
		assertEquals(true,verifica.VerificaFormato("png"));
	}
	
	@Test
	void formato_exc() {
		assertThrows(FormatoNonTrovatoException.class,()->verifica.VerificaFormato("ciao"));
	}
	
	@Test
	void parametri() throws ParametriErratiException {
		assertEquals(true,verifica.VerificaParametri(calendario2,calendario1));
	}
	
	@Test
	void parametri_exc() {
		assertThrows(ParametriErratiException.class,()->verifica.VerificaParametri(calendario1,calendario2));
	}

}
