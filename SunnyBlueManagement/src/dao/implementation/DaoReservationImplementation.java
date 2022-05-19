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
import model.Decoration;
import model.LineItem;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public class DaoReservationImplementation implements DaoReservationIF{

	Connection con = DBConnection.getInstance().getDBcon();
	
	private PreparedStatement buildCreateReservationStatement(Reservation reservation) throws SQLException {
		String query = "INSERT INTO Reservation values(?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, reservation.getDate());
		stmt.setString(2, Integer.toString(reservation.getnumOfPeople()));
		stmt.setString(3, reservation.getReservationName());
		stmt.setString(4, reservation.getSpecificRequests());
		stmt.setString(5, Long.toString(reservation.getphoneNo()));
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildCreateDinnerTableReservationStatement(Reservation reservation, Table table) throws SQLException {
		String query = 
				"BEGIN "
						+ "IF NOT EXISTS "
							+ "(SELECT reservation.reservationId, reservation.date 'reservation date', t2.dinnerTable_tableNo_FK 'table number fk' "
							+ "FROM Reservation reservation "
							+ "INNER JOIN DinnerTable_Reservation t2 "
								+ "ON reservation.reservationId = t2.reservation_reservationId_FK "
								+ "WHERE reservation.date = ? "
								+ "AND (? IN (t2.dinnerTable_tableNo_FK))) "
						+ "BEGIN "
							+ "INSERT INTO DinnerTable_Reservation (reservation_reservationId_FK, dinnerTable_tableNo_FK) "
							+ "VALUES (?, ?) "
						+ "END "
				+ "END;";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, reservation.getDate());
		stmt.setString(2, Integer.toString(table.getTableNo()));
		stmt.setString(3, Integer.toString(reservation.getReservationId()));
		stmt.setString(4, Integer.toString(table.getTableNo()));
		
		System.out.println(query);
		return stmt;
	}

	private PreparedStatement buildReadTablesByDateStatement(String date) throws SQLException {
		String query = 
				"SELECT * FROM DinnerTable "
				+ "WHERE tableNo "
				+ "NOT IN "
				+ "(SELECT dinnerTable_tableNo_FK FROM DinnerTable_Reservation "
				+ "WHERE reservation_reservationId_FK IN "
				+ "(SELECT reservationId "
				+ "FROM Reservation "
				+ "WHERE Date = ?))";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, date);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadReservationsByDateStatement(String date) throws SQLException {
		String query = 
				"SELECT * FROM Reservation "
				+ "WHERE date  = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, date);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadTablesByIDStatement(int id) throws SQLException{
		String query = 
				"SELECT * FROM DinnerTable WHERE tableNo IN("
				+ "SELECT dinnerTable_tableNo_FK FROM DinnerTable_Reservation WHERE reservation_reservationId_FK = ?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildCreateReservation_Decoration(Reservation reservation, LineItem<Decoration> lineItem) throws SQLException {
		//TODO CHECKING EDGE CASE IS HARDCODED
		String query = 
				"INSERT INTO Reservation_Decoration "
				+ "(reservation_reservationId_FK, decoration_decorationId_FK, quantity) "
				+ "SELECT ?,?,? "
					+ "WHERE EXISTS (SELECT * FROM Decoration d "
					+ "WHERE d.decorationId = ? AND (d.quantityInStock - ?) > 0); "

				+ "UPDATE Decoration "
					+ "SET quantityInStock = quantityInStock - ? "
					+ "WHERE EXISTS (SELECT * FROM Decoration d "
						+ "WHERE d.decorationId = ? AND (d.quantityInStock - ?) > 0) "
						+ "AND decorationId = ?; ";

		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(reservation.getReservationId() + " decId" + lineItem.getItem().getDecorationId());
		stmt.setInt(1, reservation.getReservationId());
		stmt.setInt(2, lineItem.getItem().getDecorationId());
		stmt.setInt(3, lineItem.getQuantity());
		
		stmt.setInt(4, lineItem.getItem().getDecorationId());
		stmt.setInt(5, lineItem.getQuantity());
		stmt.setInt(6, lineItem.getQuantity());
		stmt.setInt(7, lineItem.getItem().getDecorationId());
		stmt.setInt(8, lineItem.getQuantity());
		stmt.setInt(9, lineItem.getItem().getDecorationId());
		System.out.println(query);
		return stmt;
	}
	
	// Transaction
	@Override
	public void create(Reservation obj) throws Exception {

		PreparedStatement stmt = buildCreateReservationStatement(obj);

		try {
			con.setAutoCommit(false);
			stmt.executeUpdate();
			// TODO Return the created reservation

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				obj.setReservationID(generatedKeys.getInt(1));

			}

			for (Table table : obj.getListOfTables()) {
				createDinnerTableReservation(obj, table);
			}
			
			for (LineItem<Decoration> lineItem : obj.getListOfDecorations()) {
				createReservation_Decoration(obj, lineItem);
			}
			
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
					System.out.println("Rolling back database");
				} catch (SQLException excep) {
					throw new SQLException("Error when rolling back database" + excep);
				}
			}
			throw new Exception("sql expcetion" + e);
		} catch (NullPointerException e) {
			throw new Exception("Technical error" + e);
		} finally {
			DBConnection.closeConnection();
		}

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
	
	public Collection<Table> readTablesByDate(String date) throws Exception{
		PreparedStatement stmt = buildReadTablesByDateStatement(date);
		Collection<Table> fetchedTables = new ArrayList<>();
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			fetchedTables.add(new Table(rs.getInt(1),rs.getInt(2), rs.getBoolean(3)));
		}
		return fetchedTables;
	}
	
	private Collection<Table> readTablesById(int id) throws SQLException{
		PreparedStatement stmt = buildReadTablesByIDStatement(id);
		Collection<Table> fetchedTables = new ArrayList<>();
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			fetchedTables.add(new Table(rs.getInt(1),rs.getInt(2), rs.getBoolean(3)));
		}
		return fetchedTables;
	}
	
	public Collection<Reservation> readReservationsByDate(String date) throws Exception{
		PreparedStatement stmt = buildReadReservationsByDateStatement(date);
		Collection<Reservation> fetchedReservations = new ArrayList<>();
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Reservation reservation = new Reservation(rs.getInt(1), rs.getInt(3), rs.getString(2), rs.getString(4), rs.getString(5), rs.getLong(6));
			fetchedReservations.add(reservation);
			for(Table table : readTablesById(reservation.getReservationId())){
				reservation.addTable(table);
			}
		}		
		System.out.println(fetchedReservations.size());
		return fetchedReservations;
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
	private void createDinnerTableReservation(Reservation reservation, Table table) throws SQLException, NullPointerException, Exception{
		PreparedStatement stmt = buildCreateDinnerTableReservationStatement(reservation, table);
		int rowsUpdated = stmt.executeUpdate();
		if (rowsUpdated != 1) {
			throw new SQLException("Table reservations could be created");
		}
	}

	private void createReservation_Decoration(Reservation reservation, LineItem<Decoration> lineItem) throws SQLException, NullPointerException, Exception {
		PreparedStatement stmt = buildCreateReservation_Decoration(reservation, lineItem);
		int rowsUpdated = stmt.executeUpdate();
		if (rowsUpdated != 1) {
			throw new SQLException("Decoration could not be created");
		}
	}
}


