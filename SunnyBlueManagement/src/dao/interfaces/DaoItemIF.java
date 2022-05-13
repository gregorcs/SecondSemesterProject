package dao.interfaces;

import java.util.Collection;

import model.Decoration;
import model.Item;

public interface DaoItemIF extends DaoIF<Item> {
	
	public Collection<Item> readByName(String name) throws Exception;
	public Collection<Item> readByNameAndDepartment(String name, String departmentEnum) throws Exception;
	public Collection<Decoration> readAllDecorations() throws Exception;
}
