package dao;

import dao.implementation.DaoSupplyOrderImplementation;
import dao.interfaces.DaoSupplyOrderIF;

public class DaoFactory {

	private DaoFactory() {};
	
	public static DaoSupplyOrderIF createDaoSupplyOrder() {
		return new DaoSupplyOrderImplementation();
	}
}
