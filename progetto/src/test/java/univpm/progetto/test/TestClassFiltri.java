package univpm.progetto.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import univpm.progetto.filtri_e_statistiche.Filtri;

class TestClassFiltri {
	
	private Filtri filtro = new Filtri();
	private Calendar c1 = Calendar.getInstance();
	private Calendar c2 = Calendar.getInstance();
	private Calendar c3 = Calendar.getInstance();
	
	@BeforeEach 
	void setup() {	
		c1.set(2020,12-1,11);
		c2.set(2020,9-1,8);
		c3.set(2020,10-1,17);
	}

	@Test
	void dimensione() {
		assertEquals(true,filtro.FiltroDimensione((long)2000,(long) 1000, "<="));
	}
	
	@Test
	void data() {
		assertEquals(true,filtro.FiltroData(c1, c2, c3));
	}
	
	@Test
	void altLargh() {
		assertEquals(true,filtro.FiltroAltLargh((long)2000,(long) 1000,(long) 1000, (long)700, "<="));
	}

}
