package dao;

import dao.implementation.DaoReservationImplementation;
import dao.implementation.DaoSupplyOrderImplementation;
import dao.interfaces.DaoReservationIF;
import dao.interfaces.DaoSupplyOrderIF;

public class DaoFactory {

	private DaoFactory() {};
	
	public static DaoSupplyOrderIF createDaoSupplyOrder() {
		return new DaoSupplyOrderImplementation();
	}
	
	public static DaoReservationIF createDaoReservation() {
		return new DaoReservationImplementation();
	}
}
