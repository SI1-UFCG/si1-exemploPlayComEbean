package models;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DisciplinaTest {

	Disciplina si1 = new Disciplina("SI1", "Sistemas de Informação 1", 4);

	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void deveConseguirSalvarSemPrerequisitos() {
		si1.save();

		Disciplina recuperada = Disciplina.find.findUnique();

		assertThat(recuperada).isEqualTo(si1);
	}

	@Test
	public void deveConseguirSalvarComPrerequisitos() {
		si1.save();
		
		Disciplina si2 = new Disciplina("SI2", "Sistemas de Informação 2", 4,
				Arrays.asList(new Disciplina[] { si1 }));
		Disciplina optativa = new Disciplina("OPT", "TECC: Seiquelá", 4,
				Arrays.asList(new Disciplina[] { si1 }));

		si2.save();
		optativa.save();

		List<Disciplina> todas = Disciplina.find.all();
		assertThat(todas.size()).isEqualTo(3);
		
		
	}
}
