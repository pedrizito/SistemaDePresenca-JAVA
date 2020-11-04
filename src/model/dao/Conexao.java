package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaChamada");
	public static EntityManager em = emf.createEntityManager();
}
