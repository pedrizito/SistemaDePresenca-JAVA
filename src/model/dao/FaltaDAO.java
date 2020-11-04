package model.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Falta;

public class FaltaDAO implements GenericDAO<Falta>{
	
	@Override
	public void save(Falta object) {
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.persist(object);
			Conexao.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Aconteceu um erro ao salvar sua falta.");			
		}
	}

	@Override
	public void update(Falta object, int id) {
		System.out.println("Não editavel");
	}

	@Override
	public Falta findById(int id) {
		Falta falta = null;
		try {
			falta = Conexao.em.find(Falta.class, id);
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar essa falta em espefico.");
		}
		return falta;
	}

	@Override
	public List<Falta> findAll() {
		List<Falta> faltas = null;
		try {
			CriteriaBuilder cb = Conexao.em.getCriteriaBuilder();
			CriteriaQuery<Falta> cq = cb.createQuery(Falta.class);
			Root<Falta> rootEntry = cq.from(Falta.class);
			CriteriaQuery<Falta> all = cq.select(rootEntry);
			TypedQuery<Falta> allQuery = Conexao.em.createQuery(all);
		    faltas =  allQuery.getResultList();
			
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar todas as faltas.");
		}
		return faltas;
	}

	@Override
	public void delete(int id) {
		Falta falta = Conexao.em.find(Falta.class, id);
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.remove(falta);
			Conexao.em.getTransaction().commit();
		} catch(Exception e) {
			System.out.println("Falha ao excluir falta");
		}
	}

}
