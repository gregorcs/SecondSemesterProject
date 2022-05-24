/**
 *  The TableController class contains the CRUD functionality for the Table Object.
 *  
 *  With the utilization of the persistence layer (DAO), it is fed information regarding data operations,
 *  while maintaining a level of security for details within the database.
 */

package controller;

import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoTableIF;
import model.reservation.Table;

public class TableController {
	
	private DaoTableIF daoTable;
	
	public void createTable(int tableNo, int noOfSeats, boolean isOutside) {
		this.daoTable = DaoFactory.createDaoTable();
		Table tableToCreate = new Table(tableNo, noOfSeats, isOutside);
		try {
			daoTable.create(tableToCreate);
		} catch (Exception e) {
			//TODO print-out
			e.printStackTrace();
		}
	}
	
	public Table readTable(int tableNo) {
		this.daoTable = DaoFactory.createDaoTable();
		try {
			return daoTable.read(tableNo);
		} catch (Exception e) {
			//TODO print-out
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Table> readAllTables() {
		this.daoTable = DaoFactory.createDaoTable();
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
		this.daoTable = DaoFactory.createDaoTable();
		try {
			return daoTable.readByNoOfSeats(noOfSeats);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Table> readTableByIsOutside(boolean isOutside) {
		this.daoTable = DaoFactory.createDaoTable();
		try {
			return daoTable.readByIsOutside(isOutside);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteTable(Table table) {
		this.daoTable = DaoFactory.createDaoTable();
		try {
			daoTable.delete(table);
		} catch (Exception e) {
			//TODO print-out
			e.printStackTrace();
		}
	}
}