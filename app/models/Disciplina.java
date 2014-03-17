package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

@Entity
public class Disciplina extends Model {

	@Id
	private String codigo;

	private int creditos;
	private String nome;

	@ManyToMany
	@JoinTable(name = "dependencias", joinColumns = @JoinColumn(name = "dependente"), inverseJoinColumns = @JoinColumn(name = "requisito"))
	private List<Disciplina> prerequisitos;
	/*
	 * JoinTable sempre precisa de nome. As colunas que vão ser usadas nessa
	 * tabela precisam de nome porque senão o Ebean acaba gerando duas colunas
	 * com o mesmo nome: ele gera uma tabela com duas colunas Codigo, aqui. Uma
	 * pra a dependente e outra pra o requisito.
	 */

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
		this.prerequisitos = prerequisitos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + creditos;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
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

	public String getNome() {
		return nome;
	}

	public List<Disciplina> getPrerequisitos() {
		return prerequisitos;
	}

	public String getCodigo() {
		return codigo;
	}
}
