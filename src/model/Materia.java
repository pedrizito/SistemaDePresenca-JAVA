package model;

/*Nova classe*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "materias")
public class Materia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nome;
	
	@OneToOne(mappedBy = "materia")
	private Turma turma;
	
	public Materia() {
		
	}	

	public Materia(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Materia(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Materia [id=" + id + ", nome=" + nome + "]";
	}	
}
