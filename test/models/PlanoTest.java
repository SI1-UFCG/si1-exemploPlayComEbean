package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class PlanoTest {

	Disciplina si1 = new Disciplina("SI1", "Sistemas de Informação 1", 4);
	Disciplina si2 = new Disciplina("SI2", "Sistemas de Informação 2", 4,
			Arrays.asList(new Disciplina[] { si1 }));
	Disciplina optativa = new Disciplina("OPT", "TECC: Seiquelá", 4,
			Arrays.asList(new Disciplina[] { si1 }));
	private Grade g;

	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));
		si1.save();
		si2.save();
		optativa.save();
		g = new Grade(Disciplina.find.all());
		g.save();
	}

	@Test
	public void deveSalvarERecuperar() throws Exception {
		Grade grade = Grade.find.findUnique();
		
		Plano umPlano = new Plano(grade);
	}

}
