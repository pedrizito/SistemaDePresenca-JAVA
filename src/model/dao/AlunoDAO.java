package model.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Aluno;

public class AlunoDAO implements GenericDAO<Aluno>{

	@Override
	public void save(Aluno object) {
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.persist(object);
			Conexao.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Aconteceu um erro ao salvar sua Aluno.");			
		}
	}

	@Override
	public void update(Aluno object, int id) {
		try {
			Aluno aluno = Conexao.em.find(Aluno.class, id);
			Conexao.em.getTransaction().begin();
			aluno.setNome(object.getNome());
			aluno.setRegistroEscolar(object.getRegistroEscolar());
			Conexao.em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Aconteceu um erro ao editar seu aluno.");			
		}
	}

	@Override
	public Aluno findById(int id) {
		Aluno aluno = null;
		try {
			aluno = Conexao.em.find(Aluno.class, id);
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar esse aluno em espefico.");
		}
		return aluno;
	}

	@Override
	public List<Aluno> findAll() {
		List<Aluno> alunos = null;
		try {
			CriteriaBuilder cb = Conexao.em.getCriteriaBuilder();
			CriteriaQuery<Aluno> cq = cb.createQuery(Aluno.class);
			Root<Aluno> rootEntry = cq.from(Aluno.class);
			CriteriaQuery<Aluno> all = cq.select(rootEntry);
			TypedQuery<Aluno> allQuery = Conexao.em.createQuery(all);
		    alunos =  allQuery.getResultList();
			
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar todas as alunos.");
		}
		return alunos;
	}

	@Override
	public void delete(int id) {
		Aluno aluno = Conexao.em.find(Aluno.class, id);
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.remove(aluno);
			Conexao.em.getTransaction().commit();
		} catch(Exception e) {
			System.out.println("Falha ao excluir aluno");
		}
	}

}
