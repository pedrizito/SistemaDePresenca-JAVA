package model.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	void save(T object);
	
	void update(T object, int id);
	
	T findById(int id);
	
	List<T> findAll();
	
	void delete(int id);
}
