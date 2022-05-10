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

	public void createItem() {
		//TODO implement
	}
	
	public Collection<Item> readAll() {
		try {
			return daoItem.readAll();
		} catch (Exception e) {
			// TODO PRINT SOMETHING TO THE USER
			e.printStackTrace();
		}

		// TODO SHOULD I JUST RETURN NULL HERE? IM SCARED OF IT CRASHING THE PROGRAM
		return new ArrayList<Item>();
	}
}