package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "faltas")
public class Falta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private Date data;
	
	@Transient
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	@Column(nullable = false)
	private boolean justificavel;
	
	@ManyToOne
	private Aluno aluno;
	
	public Falta() {
		
	}	 

	public Falta(int id, Date data, boolean justificavel, Aluno aluno) {
		this.id = id;
		this.data = data;
		this.justificavel = justificavel;
		this.aluno = aluno;
	}
	
	public Falta(Date data, boolean justificavel, Aluno aluno) {
		this.data = data;
		this.justificavel = justificavel;
		this.aluno = aluno;
	}

	public int getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isJustificavel() {
		return justificavel;
	}

	public void setJustificavel(boolean justificavel) {
		this.justificavel = justificavel;
	}

	@Override
	public String toString() {
		return sdf.format(getData());
	}	
}
