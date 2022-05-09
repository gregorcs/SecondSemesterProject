package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		stmt.setString(1, Integer.toString(reservation.getReservationId()));
		stmt.setString(2, Integer.toString(table.getTableNo()));
		System.out.println(createDinnerTableReservation);
		return stmt;
	}
	
	//Transaction
	@Override
	public int create(Reservation obj) throws Exception {
		
		PreparedStatement stmt = buildCreateReservationStatement(obj);
		int insertedKey = 1;
		
		try {
		con.setAutoCommit(false);
		stmt.executeUpdate();
		//TODO Return the created reservation
		
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next()) {
			obj.setReservationID(generatedKeys.getInt(1));
			
			}
		
		for (Table table : obj.getListOfTables()) {
			createDinnerTableReservation(obj, table);
			}
		con.commit();
	
	//Error handling
	} catch (SQLException e) {
		insertedKey = -1;
		if (con != null) {
			try {
				con.rollback();
				System.out.println("Rolling back database");
			} catch(SQLException excep) {
				throw new SQLException("Error when rolling back database");
			}
		}
	} catch (NullPointerException e) {
		insertedKey = -2;
		throw new Exception("Technical error");
	} finally {
		DBConnection.closeConnection();
	}
	
	return insertedKey;
	}

	@Override
	public Reservation read(int id) throws Exception {
		String readString = "SELECT * FROM Reservation WHERE reservationId = " + id;
		Statement stmt;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(readString);
		Reservation fetchedReservation = new Reservation(rs.getInt(1), rs.getInt(3), rs.getString(2), rs.getString(4), rs.getString(5), rs.getLong(6)); //INSERT SHIT HERE IN CORRECT ORDER
		return fetchedReservation;
	}
	
	public Collection<Table> readTablesByDate(String date) throws SQLException{
		Collection<Table> fetchedTables = new ArrayList<>();
		String readTableString = "SELECT * FROM DinnerTable WHERE tableNo NOT IN (SELECT tableNo FROM DinnerTable_Reservation WHERE reservation_reservationId_FK IN "
				+ "(SELECT reservationId FROM Reservation WHERE Date = " + date + "))";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(readTableString);
		while(rs.next()) {
			fetchedTables.add(new Table(rs.getInt(1),rs.getInt(2), rs.getBoolean(3)));
		}
		
		return fetchedTables;
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
		Collection<Reservation> fetchedReservations = new ArrayList<>();
		String readAllString = "SELECT * FROM Reservation";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(readAllString);
		while(rs.next()) {
			fetchedReservations.add(new Reservation(rs.getInt(1), rs.getInt(3), rs.getString(2), rs.getString(4), rs.getString(5), rs.getLong(6))); //DUPLICATE CODE WITH OTHER READ
		}
		return fetchedReservations;
	}
	
	
	//Reservation - Table Join Table Insertion 
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


