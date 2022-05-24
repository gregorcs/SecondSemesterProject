package dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoTableIF;
import model.reservation.Table;

public class DaoTableImplementation implements DaoTableIF {
	
	Connection con = DBConnection.getInstance().getDBcon();
	
	// ******* CREATE *******
	private PreparedStatement buildCreateStatement(Table table) throws SQLException {
		String query = "INSERT INTO DinnerTable VALUES (?, ?, ?)";
		
		PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, table.getTableNo());
		stmt.setInt(2, table.getNoOfSeats());
		stmt.setBoolean(3, table.getIsOutside());
		System.out.println(query);
		return stmt;
	}

	@Override
	public void create(Table obj) throws Exception {
		PreparedStatement stmt = buildCreateStatement(obj);
		
		try {
			stmt.executeQuery();
		} catch (SQLException e) {
			throw new Exception("SQL exception " + e);
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems " + e);
		} catch (Exception e) {
			throw new Exception("Technical error " + e);
		} finally {
			DBConnection.closeConnection();
		}
	}
	
	// ******* READ *******
	private PreparedStatement buildReadTableStatement(int tableNo) throws SQLException {
		String query = "SELECT * FROM DinnerTable WHERE tableNo = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, Integer.toString(tableNo));
		System.out.println(query);
		return stmt;
	}

	@Override
	public Table read(int id) throws Exception {
		PreparedStatement stmt = buildReadTableStatement(id);
		Table table = new Table();

		try {
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				table = new Table(rs.getInt(1), rs.getInt(2), rs.getBoolean(3));
			}

		} catch (SQLException e) {
			throw new Exception("SQL exception " + e);
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems " + e);
		} catch (Exception e) {
			throw new Exception("Technical error " + e);
		} finally {
			DBConnection.closeConnection();
		}
		return table;
	}

	// ******* READ ALL *******
	private PreparedStatement buildReadAllTablesStatement() throws SQLException {
		String query = "SELECT * FROM DinnerTable";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}

	@Override
	public Collection<Table> readAll() throws Exception {
		PreparedStatement stmt = buildReadAllTablesStatement();
		ArrayList<Table> tableList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				tableList.add(new Table(rs.getInt(1), rs.getInt(2), rs.getBoolean(3)));
			}

		} catch (SQLException e) {
			throw new Exception("SQL exception " + e);
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems " + e);
		} catch (Exception e) {
			throw new Exception("Technical error " + e);
		} finally {
			DBConnection.closeConnection();
		}
		return tableList;
	}
	
	// ******* READ BY NUMBER OF SEATS *******
	private PreparedStatement buildReadTableByNoOfSeatsStatement(int noOfSeats) throws SQLException {
		String query = "SELECT * FROM DinnerTable WHERE noOfSeats = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, Integer.toString(noOfSeats));
		System.out.println(query);
		return stmt;
	}
	
	@Override
	public Collection<Table> readByNoOfSeats(int noOfSeats) throws Exception {
		PreparedStatement stmt =  buildReadTableByNoOfSeatsStatement(noOfSeats);
		ArrayList<Table> tableList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tableList.add(new Table(rs.getInt(1), rs.getInt(2), rs.getBoolean(3)));
			}
		} catch (SQLException e) {
			throw new Exception("SQL exception " + e);
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems " + e);
		} catch (Exception e) {
			throw new Exception("Technical error " + e);
		} finally {
			DBConnection.closeConnection();
		}
		return tableList;
	}

	// ******* READ BY TABLE LOCATION ((!)OUTSIDE) *******
	private PreparedStatement buildReadTableByIsOutsideStatement(boolean isOutside) throws SQLException {
		String query = "SELECT * FROM DinnerTable WHERE isOutside = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, Boolean.toString(isOutside));
		System.out.println(query);
		return stmt;
	}
	
	@Override
	public Collection<Table> readByIsOutside(boolean isOutside) throws Exception {
		PreparedStatement stmt =  buildReadTableByIsOutsideStatement(isOutside);
		ArrayList<Table> tableList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tableList.add(new Table(rs.getInt(1), rs.getInt(2), rs.getBoolean(3)));
			}
		} catch (SQLException e) {
			throw new Exception("SQL exception " + e);
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems " + e);
		} catch (Exception e) {
			throw new Exception("Technical error " + e);
		} finally {
			DBConnection.closeConnection();
		}
		return tableList;
	}
	
	// ******* UPDATE *******	
	@Override
	public void update(Table obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// ******* DELETE *******
	private PreparedStatement buildDeleteTableStatement(Table table) throws SQLException {
		String query = "DELETE FROM DinnerTable WHERE tableNo = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, Integer.toString(table.getTableNo()));
		System.out.println(query);
		return stmt;
	}
	
	@Override
	public void delete(Table obj) throws Exception {
		PreparedStatement stmt = buildDeleteTableStatement(obj);
		try {
			stmt.executeQuery();
		} catch (SQLException e) {
			throw new Exception("SQL exception " + e);
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems " + e);
		} catch (Exception e) {
			throw new Exception("Technical error " + e);
		} finally {
			DBConnection.closeConnection();
		}
	}
}