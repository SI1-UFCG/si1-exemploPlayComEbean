package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Disciplina extends Model {

	@Id
	private String codigo;
	private int creditos;
	private String nome;

	public static Model.Finder<String, Disciplina> find = new Model.Finder<String, Disciplina>(
			String.class, Disciplina.class);

	public Disciplina() {
	}

	public Disciplina(String codigo, String nome, int creditos) {
		this(codigo, nome, creditos, new LinkedList<Disciplina>());
	}

	public Disciplina(String codigo, String nome, int creditos,
			List<Disciplina> prerequisitos) {
		this.codigo = codigo;
		this.nome = nome;
		this.creditos = creditos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + creditos;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (creditos != other.creditos)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
