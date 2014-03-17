package models;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GradeTest {

	Disciplina si1 = new Disciplina("SI1", "Sistemas de Informação 1", 4);
	Disciplina si2 = new Disciplina("SI2", "Sistemas de Informação 2", 4,
			Arrays.asList(new Disciplina[] { si1 }));
	Disciplina optativa = new Disciplina("OPT", "TECC: Seiquelá", 4,
			Arrays.asList(new Disciplina[] { si1 }));

	@Before
	public void setUp() throws Exception {
		start(fakeApplication(inMemoryDatabase()));
		si1.save();
		si2.save();
		optativa.save();
	}

	@Test
	public void deveSalvarERecuperar() throws Exception {
		Grade g = new Grade(Disciplina.find.all());
		g.save();

		Grade recuperado = Grade.find.findUnique();
		assertThat(recuperado).isEqualTo(g);
		
		assertThat(recuperado.getDisciplinas().size()).isEqualTo(3);
		for (Disciplina d : recuperado.getDisciplinas()) {
			if(d.getCodigo().equals("SI2")){
				assertThat(d).isEqualTo(si2);
			}
		}
		assertThat(g.getDisciplinas().size()).isEqualTo(3);
	}

}
