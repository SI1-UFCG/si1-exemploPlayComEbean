package models;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import models.Disciplina;

import org.junit.Before;
import org.junit.Test;

public class DsiciplinaTest {

	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void deveConseguirSalvar() {
		Disciplina d = new Disciplina("SI1", "Sistemas de Informação 1", 4);
		d.save();

		// recupera
		Disciplina recuperada = Disciplina.find.findUnique();

		assertThat(recuperada).equals(d);
	}

}
