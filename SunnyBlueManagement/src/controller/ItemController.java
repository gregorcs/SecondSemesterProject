package controller;

import java.util.ArrayList;
import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoItemIF;
import model.Item;

public class ItemController {
	
	private DaoItemIF daoItem;

	public ItemController() {
		super();
		this.daoItem = DaoFactory.createDaoItem();
	}

	public void createItem(String name, String departmentType) {
		Item itemToCreate = new Item(name, departmentType.toLowerCase());
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
		return null;
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
			Collection<Item> itemList;
			itemList = daoItem.readAll();
			itemList.forEach(item -> item.setDepartmentType(item.getDepartmentType().toLowerCase()));
			return itemList;
		} catch (Exception e) {
			// TODO PRINT TO USER
			e.printStackTrace();
		}
		// TODO SHOULD I JUST RETURN NULL HERE? IM SCARED OF IT CRASHING THE PROGRAM
		return null;
	}
	
	public Collection<Item> readItemByName(String name) {
		try {
			return daoItem.readByName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Item> readItemByNameAndDepartment(String name, String department) {
		try {
			return daoItem.readByNameAndDepartment(name, department);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<String> getAllDepartmentTypes() {
		Collection<String> departmentsList = new ArrayList<String>();
		for (Item item : readAllItems()) {
			if (!departmentsList.contains(item.getDepartmentType())) {
				departmentsList.add(item.getDepartmentType());
			}
		}
		return departmentsList;
	}
























}