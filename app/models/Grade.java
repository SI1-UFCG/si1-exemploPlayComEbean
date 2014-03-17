package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@Entity
public class Grade extends Model {

	@Id
	private Long id;

	/*
	 * Por causa de como o Ebean funciona, é mais fácil colocar relações
	 * OneToMany unidirecionais como ManyToMany. Na prática, gasta um pouco mais
	 * de espaço, mas fica muito mais simples.
	 */
	@ManyToMany
	@JoinTable(name = "Grade_Disciplinas")
	private List<Disciplina> disciplinas;

	public static Model.Finder<Long, Grade> find = new Model.Finder<Long, Grade>(
			Long.class, Grade.class);

	public Grade(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

}
