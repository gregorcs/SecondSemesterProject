package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoReservationIF;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

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
	
	//prepared statement for DinnerTable - Reservation Join Table
	//should we name it like it is in the relational model or should it still just be table
	private PreparedStatement buildCreateDinnerTableReservationStatement(Reservation reservation, Table table) throws SQLException{
		String createDinnerTableReservation = "INSERT INTO DinnerTable_Reservation values (?, ?)";
		PreparedStatement stmt = con.prepareStatement(createDinnerTableReservation, Statement.RETURN_GENERATED_KEYS);
	//getReservationID needs to be implemented??
		stmt.SetString(1, Integer.toString(reservation.getReservationID()));
		stmt.SetString(2, Integer.toString(table.getTable().getTableNo()));
		System.out.println(createDinnerTableReservation);
		return stmt;
	}
	
	//TODO Transaction
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
	
	private int createDinnerTableReservation(Reservation reservation, Table table) throws SQLException, NullPointerException, Exception{
		
		PreparedStatement stmt = buildCreateDinnerTableReservationStatement(reservation, table);
		int insertedKey = 1;
		
		try {
			ResultSet rs = stmt.executeQuery();
			//TODO RETURN THE CREATED RESERVATION
		} catch (SQLException e) {
			insertedKey = -1;
			throw new SQLException("SQL exception");
		} catch (NullPointerException e) {
			insertedKey = -2;
		throw new NullPointerException("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			insertedKey = -3;
			throw new Exception ("Technical error");
		} finally {
		//TODO REMOVED CLOSE.CONNECTION FROM HERE TO TEST IF TRANSACTION WORKS
		}
		
		return insertedKey;
	}
	
}


