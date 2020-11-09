package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "turmas")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private char classe;
	
	@Enumerated(EnumType.STRING)
	private Serie serie;
	
	@OneToMany(mappedBy = "turma")
	private List<Aluno> alunos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "materia_id")
	private Materia materia;
	
	@OneToMany(mappedBy = "turma")
	private List<DiaLetivo> diasLetivo = new ArrayList<>();
	
	public Turma() {
		
	}

	public Turma(int id, char classe, Serie serie, Materia materia) {
		this.id = id;
		this.classe = classe;
		this.serie = serie;
		this.materia = materia;
	}
	
	public Turma(char classe, Serie serie, Materia materia) {
		this.classe = classe;
		this.serie = serie;
		this.materia = materia;
	}

	public int getId() {
		return id;
	}

	public char getClasse() {
		return classe;
	}

	public void setClasse(char classe) {
		this.classe = classe;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void addAlunos(Aluno aluno) {
		alunos.add(aluno);
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public Turma duplicarSerie(Materia materia) {
		Turma temp = this;
		temp.setMateria(materia);
		return temp;
	}
	
	public List<DiaLetivo> getDiasLetivo(){
		return diasLetivo;
	}

	public void addDiaLetivo(DiaLetivo date) {
		diasLetivo.add(date);
		
	}
	@Override
	public String toString() {
		return "Turma [id=" + id + ", classe=" + classe + ", serie=" + serie + ", alunos=" + alunos + ", materia="
				+ materia + "]";
	}	
}
