package dao;

import dao.implementation.DaoDecorationImplementation;
import dao.implementation.DaoItemImplementation;
import dao.implementation.DaoSupplyOrderImplementation;
import dao.implementation.DaoTableImplementation;
import dao.interfaces.DaoDecorationIF;
import dao.interfaces.DaoItemIF;
import dao.interfaces.DaoSupplyOrderIF;
import dao.interfaces.DaoTableIF;

public class DaoFactory {

	private DaoFactory() {};
	
	public static DaoSupplyOrderIF createDaoSupplyOrder() {
		return new DaoSupplyOrderImplementation();
	}
	
	public static DaoItemIF createDaoItem () {
		return new DaoItemImplementation();
	}
	
	public static DaoTableIF createDaoTable() {
		return new DaoTableImplementation();
	}
	
	public static DaoDecorationIF createDaoDecoration() {
		return new DaoDecorationImplementation();
	}
}