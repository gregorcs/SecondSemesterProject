package controller;

import java.util.ArrayList;
import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoItemIF;
import model.Item;

public class ItemController {
	
	private DaoItemIF daoItem;

	public void createItem(String name, String departmentType) {
		this.daoItem = DaoFactory.createDaoItem();
		Item itemToCreate = new Item(name, departmentType.toLowerCase());
		try {
			daoItem.create(itemToCreate);
		} catch (Exception e) {
			// TODO PRINT TO USER
			e.printStackTrace();
		}
	}
	
	public Item readItem(int id) {
		this.daoItem = DaoFactory.createDaoItem();
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
		this.daoItem = DaoFactory.createDaoItem();
		try {
			daoItem.delete(item);
		} catch (Exception e) {
			// TODO PRINT TO USER
			e.printStackTrace();
		}
	}
	
	public Collection<Item> readAllItems() {
		this.daoItem = DaoFactory.createDaoItem();
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
		this.daoItem = DaoFactory.createDaoItem();
		try {
			return daoItem.readByName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Item> readItemByNameAndDepartment(String name, String department) {
		this.daoItem = DaoFactory.createDaoItem();
		try {
			return daoItem.readByNameAndDepartment(name, department);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO MOVE LOGIC OUT OF HERE
	public Collection<String> getAllDepartmentTypes() {
		this.daoItem = DaoFactory.createDaoItem();
		Collection<String> departmentsList = new ArrayList<String>();
		for (Item item : readAllItems()) {
			if (!departmentsList.contains(item.getDepartmentType())) {
				departmentsList.add(item.getDepartmentType());
			}
		}
		return departmentsList;
	}

	public Collection<Item> readItemByNameSortById(String nameFromSearchTextField, String sortByChoice) {
		this.daoItem = DaoFactory.createDaoItem();
		try {
			if (sortByChoice.equals("LOWEST")) {
				return daoItem.readByNameSortByIdASC(nameFromSearchTextField);
			} else {
				return daoItem.readByNameSortByIdDESC(nameFromSearchTextField);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
























}