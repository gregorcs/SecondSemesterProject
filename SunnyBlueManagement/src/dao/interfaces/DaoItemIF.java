package dao.interfaces;

import java.util.Collection;

import model.Item;

public interface DaoItemIF extends DaoIF<Item> {
	
	public Collection<Item> readByName(String name) throws Exception;
}
