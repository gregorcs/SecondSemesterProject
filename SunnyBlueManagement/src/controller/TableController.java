/**
 *  The TableController class contains the CRUD functionality for the Table Object.
 *  
 *  With the utilization of the persistence layer (DAO), it is fed information regarding data operations,
 *  while maintaining a level of security for details within the database.
 */

package controller;

import java.util.Collection;

import dao.DaoFactory;
import dao.implementation.DaoTableImplementation;
import dao.interfaces.DaoTableIF;
import model.ReservationFolder.Table;

public class TableController {
	private DaoTableIF daoTable;
	
	/**
	 * Constructor
	 */
	
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
	
	public Collection<Table> readTableByIsOutside(boolean isOutside) {
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