package dao;

import dao.implementation.DaoItemImplementation;
import dao.implementation.DaoSupplyOrderImplementation;
import dao.interfaces.DaoItemIF;
import dao.interfaces.DaoSupplyOrderIF;

public class DaoFactory {

	private DaoFactory() {};
	
	public static DaoSupplyOrderIF createDaoSupplyOrder() {
		return new DaoSupplyOrderImplementation();
	}
	
	public static DaoItemIF createDaoItem () {
		return new DaoItemImplementation();
	}
}
