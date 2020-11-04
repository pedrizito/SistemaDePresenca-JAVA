package application;

import java.util.Date;

import model.Aluno;
import model.DiaLetivo;
import model.Falta;
import model.Materia;
import model.Serie;
import model.Turma;
import model.dao.AlunoDAO;
import model.dao.DiaLetivoDAO;
import model.dao.FaltaDAO;
import model.dao.GenericDAO;
import model.dao.MateriaDAO;
import model.dao.TurmaDAO;

public class Program {
	
	public static void main(String[] args) {	
		
		GenericDAO<Turma> x = new TurmaDAO();
		GenericDAO<Materia> y = new MateriaDAO();
		GenericDAO<Aluno> q = new AlunoDAO();
		GenericDAO<Falta> w = new FaltaDAO();
		GenericDAO<DiaLetivo> e = new DiaLetivoDAO();
		
		Materia materia = new Materia ("Historia");
		Turma turma = new Turma('A', Serie.PRIMEIRA_SERIE, materia);
		Aluno aluno1 = new Aluno("Matheus", "1234", turma);
		Falta falta = new Falta(new Date(), true, aluno1);
		
		try {	
			y.save(materia);
			x.save(turma);
			q.save(aluno1);
			w.save(falta);
			
			x.save(turma);
			System.out.println(x.findAll());			
			}
			catch(Exception s) {
				System.out.println("Aconteceu um erro.");
			}
		}
			
	}
		


