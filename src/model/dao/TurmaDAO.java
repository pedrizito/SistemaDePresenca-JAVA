package model.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Turma;

public class TurmaDAO implements GenericDAO<Turma>{

	@Override
	public void save(Turma object) {
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.persist(object);
			Conexao.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Aconteceu um erro ao salvar sua turma.");			
		}
	}

	@Override
	public void update(Turma object, int id) {
		try {
			Turma turma = Conexao.em.find(Turma.class, id);
			Conexao.em.getTransaction().begin();
			turma.setClasse(object.getClasse());
			turma.setMateria(object.getMateria());
			turma.setSerie(object.getSerie());
			Conexao.em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Aconteceu um erro ao editar sua turma.");			
		}
	}

	@Override
	public Turma findById(int id) {
		Turma turma = null;
		try {
			turma = Conexao.em.find(Turma.class, id);
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar essa turma em espefico.");
		}
		return turma;
	}

	@Override
	public List<Turma> findAll() {
		List<Turma> turmas = null;
		try {
			CriteriaBuilder cb = Conexao.em.getCriteriaBuilder();
			CriteriaQuery<Turma> cq = cb.createQuery(Turma.class);
			Root<Turma> rootEntry = cq.from(Turma.class);
			CriteriaQuery<Turma> all = cq.select(rootEntry);
			TypedQuery<Turma> allQuery = Conexao.em.createQuery(all);
		    turmas =  allQuery.getResultList();
			
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar todas as turmas.");
		}
		return turmas;
	}

	@Override
	public void delete(int id) {
		Turma turma = Conexao.em.find(Turma.class, id);
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.remove(turma);
			Conexao.em.getTransaction().commit();
		} catch(Exception e) {
			System.out.println("Falha ao excluir turma");
		}
	}
	

}
