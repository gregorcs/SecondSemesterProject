package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoReservationIF;
import model.ReservationFolder.Reservation;

public class DaoReservationImplementation implements DaoReservationIF{

	Connection con = DBConnection.getInstance().getDBcon();
	
	private PreparedStatement buildCreateReservationStatement(Reservation reservation) throws SQLException {
		String createReservation = "INSERT INTO Reservation values(?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = con.prepareStatement(createReservation, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, reservation.getDate());
		stmt.setString(2, Integer.toString(reservation.getnumOfPeople()));
		stmt.setString(3, reservation.getReservationName());
		stmt.setString(4, reservation.getSpecificRequests());
		stmt.setString(5, Long.toString(reservation.getphoneNo()));

		return stmt;
	}
	
	@Override
	public int create(Reservation obj) throws Exception {
		PreparedStatement stmt = buildCreateReservationStatement(obj);
		int insertedKey = 1;
		
		stmt.executeUpdate();
		
		return 0;
	}

	@Override
	public Reservation read(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Reservation obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Reservation obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Reservation> readAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
