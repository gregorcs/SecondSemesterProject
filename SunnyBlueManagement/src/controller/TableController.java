package controller;

import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoTableIF;
import model.Table;

public class TableController {
	private DaoTableIF daoTable;
	
	public TableController() {
		super();
		this.daoTable = DaoFactory.createDaoTable();
	}
	
	public Table createTable(int tableNo, int noOfSeats, boolean isOutside) {
		//TODO implement
		return null;
	}
	
	public Table readTable(int tableNo) {
		//TODO implement
		return null;
		
	}
	
	public Table update(Table table) {
		//TODO implement
		return null;
	}
	
	public void delete(Table table) {
		//TODO implement
	}
	
	public Collection<Table> findById(int tableNo) {
		//TODO implement
		return null;
		
	}
	
	public Collection<Table> readAll() {
		//TODO implement
		return null;
		
	}
}