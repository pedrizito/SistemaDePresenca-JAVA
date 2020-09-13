package application;

/*Nova classe*/

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Aluno;
import model.Falta;
import model.Materia;
import model.Serie;
import model.Turma;

public class Program {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaChamada");
		EntityManager em = emf.createEntityManager();
		
		Materia materia = new Materia ("Biologia");
		Turma turma = new Turma('A', Serie.PRIMEIRA_SERIE, materia);
		Aluno aluno1 = new Aluno("Matheus", "1234", turma);
		Falta falta = new Falta(new Date(), true, aluno1);
		
		em.getTransaction().begin();
		em.persist(materia);
		em.persist(turma);
		em.persist(aluno1);
		em.persist(falta);		
		em.getTransaction().commit();
		
	}
}
