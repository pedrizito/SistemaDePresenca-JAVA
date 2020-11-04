package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "dias_letivo")
public class DiaLetivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private Date date;
	
	@ManyToOne
	private Turma turma;
	
	public DiaLetivo() {
		
	}
	
	public DiaLetivo(Date date, Turma turma) {
		this.date = date;
		this.turma = turma;
	}

	public DiaLetivo(int id, Date date, Turma turma) {
		this.id = id;
		this.date = date;
		this.turma = turma;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	
}
