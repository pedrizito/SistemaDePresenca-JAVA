package model.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Materia;

public class MateriaDAO implements GenericDAO<Materia>{
	
	@Override
	public void save(Materia object) {
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.persist(object);
			Conexao.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Aconteceu um erro ao salvar sua materia.");			
		}
	}

	@Override
	public void update(Materia object, int id) {
		try {
			Materia materia = Conexao.em.find(Materia.class, id);
			Conexao.em.getTransaction().begin();
			materia.setNome(object.getNome());
			Conexao.em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Aconteceu um erro ao editar sua materia.");			
		}
	}

	@Override
	public Materia findById(int id) {
		Materia materia = null;
		try {
			materia = Conexao.em.find(Materia.class, id);
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar essa materia em espefico.");
		}
		return materia;
	}

	@Override
	public List<Materia> findAll() {
		List<Materia> materias = null;
		try {
			CriteriaBuilder cb = Conexao.em.getCriteriaBuilder();
			CriteriaQuery<Materia> cq = cb.createQuery(Materia.class);
			Root<Materia> rootEntry = cq.from(Materia.class);
			CriteriaQuery<Materia> all = cq.select(rootEntry);
			TypedQuery<Materia> allQuery = Conexao.em.createQuery(all);
		    materias =  allQuery.getResultList();
			
		} catch(Exception e) {
			System.out.println("Aconteceu um erro ao consultar todas as materias.");
		}
		return materias;
	}

	@Override
	public void delete(int id) {
		Materia materia = Conexao.em.find(Materia.class, id);
		try {
			Conexao.em.getTransaction().begin();
			Conexao.em.remove(materia);
			Conexao.em.getTransaction().commit();
		} catch(Exception e) {
			System.out.println("Falha ao excluir materia");
		}
	}

}
