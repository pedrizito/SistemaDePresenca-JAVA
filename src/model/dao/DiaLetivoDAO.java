package model.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.DiaLetivo;

public class DiaLetivoDAO implements GenericDAO<DiaLetivo> {
	
	@Override
	public void save(DiaLetivo object) {
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.persist(object);
			Conexao.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Aconteceu um erro ao salvar sua diaLetivo.");			
		}
	}

	@Override
	public void update(DiaLetivo object, int id) {
		System.out.println("Não editavel");
	}

	@Override
	public DiaLetivo findById(int id) {
		DiaLetivo diaLetivo = null;
		try {
			diaLetivo = Conexao.em.find(DiaLetivo.class, id);
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar essa diaLetivo em espefico.");
		}
		return diaLetivo;
	}

	@Override
	public List<DiaLetivo> findAll() {
		List<DiaLetivo> diaLetivos = null;
		try {
			CriteriaBuilder cb = Conexao.em.getCriteriaBuilder();
			CriteriaQuery<DiaLetivo> cq = cb.createQuery(DiaLetivo.class);
			Root<DiaLetivo> rootEntry = cq.from(DiaLetivo.class);
			CriteriaQuery<DiaLetivo> all = cq.select(rootEntry);
			TypedQuery<DiaLetivo> allQuery = Conexao.em.createQuery(all);
		    diaLetivos =  allQuery.getResultList();
			
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar todas as diaLetivos.");
		}
		return diaLetivos;
	}

	@Override
	public void delete(int id) {
		DiaLetivo diaLetivo = Conexao.em.find(DiaLetivo.class, id);
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.remove(diaLetivo);
			Conexao.em.getTransaction().commit();
		} catch(Exception e) {
			System.out.println("Falha ao excluir");
		}
	}

}
