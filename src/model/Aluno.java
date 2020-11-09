package model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "alunos")
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String registroEscolar;
	
	@ManyToOne()
	private Turma turma;
	
	@OneToMany(mappedBy = "aluno")
	private List<Falta> faltas = new ArrayList<>();
	
	public Aluno() {
		
	}	
	
	public Aluno(String nome, String registroEscolar, Turma turma) {
		this.nome = nome;
		this.registroEscolar = registroEscolar;
		this.turma = turma;
	}

	public Aluno(int id, String nome, String registroEscolar, Turma turma) {
		this.id = id;
		this.nome = nome;
		this.registroEscolar = registroEscolar;
		this.turma = turma;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegistroEscolar() {
		return registroEscolar;
	}

	public void setRegistroEscolar(String registroEscolar) {
		this.registroEscolar = registroEscolar;
	}

	public List<Falta> getFaltas() {
		return faltas;
	}

	public void addFaltas(Falta falta) {
		faltas.add(falta);
	}
	
	public void setTurma(Turma turma)	{
		this.turma = turma;
	}
	
	public Turma getTurma()	{
		return turma;
	}
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", registroEscolar=" + registroEscolar + ", faltas=" + faltas
				+ "]";
	}
}
