package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import dao.DBConnection;
import dao.DaoFactory;
import dao.interfaces.DaoReservationIF;
import model.Decoration;
import model.LineItem;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public class DaoReservationImplementation implements DaoReservationIF{

	Connection con = DBConnection.getInstance().getDBcon();
	
	private PreparedStatement buildCreateReservationStatement(Reservation reservation) throws SQLException {
		String query = "INSERT INTO Reservation (date, amountOfPeople, reservationName, specificRequirements, phoneNo, isEvent) values(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, reservation.getDate());
		stmt.setString(2, Integer.toString(reservation.getNumOfPeople()));
		stmt.setString(3, reservation.getReservationName());
		stmt.setString(4, reservation.getSpecificRequests());
		stmt.setString(5, Long.toString(reservation.getPhoneNo()));
		stmt.setBoolean(6, reservation.isEvent());
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
		return stmt;
	}
	
	private PreparedStatement buildReadReservation_DecorationStatement(int id) throws SQLException{
		String query = "SELECT decoration_decorationId_FK, quantity FROM Reservation_Decoration WHERE reservation_reservationId_FK = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildCreateReservation_DecorationStatement(Reservation reservation, LineItem<Decoration> lineItem) throws SQLException {
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
	
	private PreparedStatement buildDeleteReservationStatement(Reservation reservation) throws SQLException {
		String query = "DELETE FROM Reservation WHERE reservationId = ?"; 
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, reservation.getReservationId());
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildDeleteReservation_DecorationStatement(int id) throws SQLException {
		String query = "DELETE FROM Reservation_Decoration WHERE reservation_reservationId_FK = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildDeleteDinnerTable_ReservationStatement(int id) throws SQLException{
		String query = "DELETE FROM DinnerTable_Reservation WHERE reservation_reservationId_FK = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);
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
			con.setAutoCommit(true);
			DBConnection.closeConnection();
		}

	}

	@Override
	public Reservation read(int id) throws Exception {
		String readString = "SELECT * FROM Reservation WHERE reservationId = " + id;
		Statement stmt;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(readString);
		Reservation fetchedReservation = constructReservation(rs);
		return fetchedReservation;
	}
	
	public Collection<Table> readTablesByDate(String date) throws Exception{
		PreparedStatement stmt = buildReadTablesByDateStatement(date);
		Collection<Table> fetchedTables = new ArrayList<>();
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			fetchedTables.add(constructTable(rs));
		}
		return fetchedTables;
	}
	
	private Collection<Table> readTablesById(int id) throws SQLException{
		PreparedStatement stmt = buildReadTablesByIDStatement(id);
		Collection<Table> fetchedTables = new ArrayList<>();
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			fetchedTables.add(constructTable(rs));
		}
		return fetchedTables;
	}
	
	public Collection<Reservation> readReservationsByDate(String date) throws Exception{
		PreparedStatement stmt = buildReadReservationsByDateStatement(date);
		Collection<Reservation> fetchedReservations = new ArrayList<>();
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Reservation reservation = constructReservation(rs);
			fetchedReservations.add(reservation);
			for(Table table : readTablesById(reservation.getReservationId())){
				reservation.addTable(table);
			}
			if(reservation.isEvent()) {
				stmt = buildReadReservation_DecorationStatement(reservation.getReservationId());
				rs = stmt.executeQuery();
				while(rs.next()) {
					Decoration decoration = DaoFactory.createDaoDecoration().read(rs.getInt(1));
					if(decoration != null) {
						reservation.addDecoration(new LineItem<Decoration>(rs.getInt(2), decoration));
					}
				}
			}
		}		
		return fetchedReservations;
	}
	
	

	@Override
	public void update(Reservation obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Reservation obj) throws Exception {
		try {
			con.setAutoCommit(false);
			PreparedStatement stmt = buildDeleteDinnerTable_ReservationStatement(obj.getReservationId());
			stmt.executeUpdate();
			//if(obj.isEvent()) {		//isEvent() = broken
				stmt = buildDeleteReservation_DecorationStatement(obj.getReservationId());
				stmt.executeUpdate();
			//}
			stmt = buildDeleteReservationStatement(obj);
			stmt.executeUpdate();
			con.commit();
		}
		catch (SQLException e) {
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
			con.setAutoCommit(true);
			DBConnection.closeConnection();
		}
	}

	@Override
	public Collection<Reservation> readAll() throws Exception {
		Collection<Reservation> fetchedReservations = new ArrayList<>();
		String readAllString = "SELECT * FROM Reservation";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(readAllString);
		while(rs.next()) {
			fetchedReservations.add(constructReservation(rs));
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
		PreparedStatement stmt = buildCreateReservation_DecorationStatement(reservation, lineItem);
		int rowsUpdated = stmt.executeUpdate();
		if (rowsUpdated != 1) {
			throw new SQLException("Decoration could not be created");
		}
	}
	
	private Reservation constructReservation(ResultSet rs) throws SQLException {
		return new Reservation(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getLong(6), rs.getBoolean(7));
	}
	
	private Table constructTable(ResultSet rs) throws SQLException {
		return new Table(rs.getInt(1),rs.getInt(2), rs.getBoolean(3));
	}
}


