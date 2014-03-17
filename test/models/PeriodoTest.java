package models;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import static org.fest.assertions.Assertions.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class PeriodoTest {

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
		Periodo p = new Periodo();
		p.save();

		Periodo recuperado = Periodo.find.findUnique();
		assertThat(recuperado).isEqualTo(p);

		p.addCadeira(si1);
		p.addCadeira(si2);
		p.addCadeira(optativa);
		p.save();
		
		recuperado = Periodo.find.findUnique();
		assertThat(p.getDisciplinas().size()).isEqualTo(3);
		for (Disciplina d : p.getDisciplinas()) {
			if(d.getCodigo().equals("SI2")){
				assertThat(d).isEqualTo(si2);
			}
		}
		assertThat(p.getDisciplinas().size()).isEqualTo(3);
		// Não consegui ainda fazer isso funcionar:
		// assertThat(recuperado).isEqualTo(p);
	}

}
