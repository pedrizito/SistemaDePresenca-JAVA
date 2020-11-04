package application;

import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Aluno;
import model.Falta;
import model.Materia;
import model.Serie;
import model.Turma;
import model.dao.Conexao;
import view.TelaPrincipal;

class Teste {

	@Test
	void test() {
		Materia materia = new Materia ("Historia");
		Turma turma = new Turma('A', Serie.PRIMEIRA_SERIE, materia);
		Aluno aluno1 = new Aluno("Matheus", "1234", turma);
		Falta falta = new Falta(new Date(), true, aluno1);
		
		TelaPrincipal frame = new TelaPrincipal();
		frame.setVisible(true);
		
		
		try {
		Conexao.em.getTransaction().begin();
		Conexao.em.persist(materia);
		Conexao.em.persist(turma);
		Conexao.em.persist(aluno1);
		Conexao.em.persist(falta);
		Conexao.em.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println("Aconteceu um erro.");
		}
	}

}
