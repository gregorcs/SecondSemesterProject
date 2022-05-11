package controller;

import java.util.ArrayList;
import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoItemIF;
import model.DepartmentEnum;
import model.Item;

public class ItemController {
	
	private DaoItemIF daoItem;
	
	public ItemController() {
		super();
		this.daoItem = DaoFactory.createDaoItem();
	}

	public void createItem(String name, DepartmentEnum departmentType) {
		Item itemToCreate = new Item(name, departmentType);
		try {
			daoItem.create(itemToCreate);
		} catch (Exception e) {
			// TODO PRINT TO USER
			e.printStackTrace();
		}
	}
	
	public Item readItem(int id) {
		try {
			return daoItem.read(id);
		} catch (Exception e) {
			// TODO PRINT TO USER
			e.printStackTrace();
		}
		// TODO return null here too rather?
		return new Item();
	}
	
	public void deleteItem(Item item) {
		try {
			daoItem.delete(item);
		} catch (Exception e) {
			// TODO PRINT TO USER
			e.printStackTrace();
		}
	}
	
	public Collection<Item> readAllItems() {
		try {
			return daoItem.readAll();
		} catch (Exception e) {
			// TODO PRINT TO USER
			e.printStackTrace();
		}
		// TODO SHOULD I JUST RETURN NULL HERE? IM SCARED OF IT CRASHING THE PROGRAM
		return new ArrayList<Item>();
	}
	
	public Collection<Item> readByNameItem(String name) {
		try {
			return daoItem.readByName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Item>();
	}
}
