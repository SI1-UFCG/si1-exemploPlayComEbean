package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import org.junit.Before;

public class PeriodoTest {

	Disciplina si1 = new Disciplina("SI1", "Sistemas de Informação 1", 4);

	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));
	}

}
