package dao.interfaces;

import java.util.Collection;

public interface DaoIF<T> {

	public int create(T obj) throws Exception;
	public T read(int id) throws Exception;
	public void update(T obj) throws Exception;
	public void delete(T obj) throws Exception;
	public Collection<T> readAll() throws Exception;
}
