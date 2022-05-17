package dao.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public interface DaoReservationIF extends DaoIF<Reservation>{

	public Collection<Table> readTablesByDate(String date) throws SQLException;
}
