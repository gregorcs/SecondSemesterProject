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
	
	public void createTable(int tableNo, int noOfSeats, boolean isOutside) {
		Table tableToCreate = new Table(tableNo, noOfSeats, isOutside);
		try {
			daoTable.create(tableToCreate);
		} catch (Exception e) {
			//TODO print-out
			e.printStackTrace();
		}
	}
	
	public Table readTable(int tableNo) {
		try {
			return daoTable.read(tableNo);
		} catch (Exception e) {
			//TODO print-out
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Table> readAllTables() {
		try {
			Collection<Table> tableList;
			tableList = daoTable.readAll();
			return tableList;
		} catch (Exception e) {
			//TODO GUI print-out
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Table> readTableByNoOfSeats(int noOfSeats) {
		try {
			return daoTable.readByNoOfSeats(noOfSeats);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Table> readTableByIsOutisde(boolean isOutside) {
		try {
			return daoTable.readByIsOutside(isOutside);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteTable(Table table) {
		try {
			daoTable.delete(table);
		} catch (Exception e) {
			//TODO print-out
			e.printStackTrace();
		}
	}
}