package dao.interfaces;

import java.util.Collection;

import model.reservation.Reservation;
import model.reservation.Table;

public interface DaoReservationIF extends DaoIF<Reservation>{

	public Collection<Table> readTablesByDate(String date) throws Exception;
	
	public Collection<Reservation> readReservationsByDate(String date) throws Exception;
}
