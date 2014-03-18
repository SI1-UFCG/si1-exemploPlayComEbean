package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@Entity
public class Periodo extends Model {

	@Id
	private Long id;

	@ManyToMany
	@JoinTable(name = "Periodo_Disciplina")
	private List<Disciplina> disciplinas;

	public static Model.Finder<Long, Periodo> find = new Model.Finder<Long, Periodo>(
			Long.class, Periodo.class);

	public Periodo() {
		this.disciplinas = new LinkedList<Disciplina>();
	}

	public void addCadeira(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime
				+ ((disciplinas == null) ? 0 : disciplinas.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Periodo other = (Periodo) obj;
		if (disciplinas == null) {
			if (other.disciplinas != null)
				return false;
		} else if (!disciplinas.equals(other.disciplinas))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
