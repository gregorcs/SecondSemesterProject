package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoTableIF;
import model.Table;

public class DaoTableImplementation implements DaoTableIF {
	Connection con = DBConnection.getInstance().getDBcon();
	
	// ******* CREATE *******
	private PreparedStatement buildCreateString(Table table) throws SQLException {
		String createTableString = "INSERT INTO DinnerTable VALUES (?, ?, ?)";
		
		PreparedStatement stmt = con.prepareStatement(createTableString, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, table.getTableNo());
		stmt.setInt(2, table.getNoOfSeats());
		stmt.setBoolean(3, table.getIsOutside());
		System.out.println(createTableString);
		return stmt;
	}

	@Override
	public void create(Table obj) throws Exception {
		PreparedStatement stmt = buildCreateString(obj);
		
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
	private PreparedStatement buildReadTableString(int tableNo) throws SQLException {
		String readTableString = "SELECT * FROM DinnerTable WHERE tableNo = ?";
		PreparedStatement stmt = con.prepareStatement(readTableString);
		stmt.setString(1, Integer.toString(tableNo));
		System.out.println(readTableString);
		return stmt;
	}

	@Override
	public Table read(int id) throws Exception {
		PreparedStatement stmt = buildReadTableString(id);
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
	private PreparedStatement buildReadAllTablesString() throws SQLException {
		String readAllString = "SELECT * FROM DinnerTable";
		PreparedStatement stmt = con.prepareStatement(readAllString);
		System.out.println(readAllString);
		return stmt;
	}

	@Override
	public Collection<Table> readAll() throws Exception {
		PreparedStatement stmt = buildReadAllTablesString();
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
	private PreparedStatement buildReadTableByNoOfSeatsString(int noOfSeats) throws SQLException {
		String readTableByNoOfSeats = "SELECT * FROM DinnerTable WHERE noOfSeats = ?";
		PreparedStatement stmt = con.prepareStatement(readTableByNoOfSeats);
		stmt.setString(2, Integer.toString(noOfSeats));
		System.out.println(readTableByNoOfSeats);
		return stmt;
	}
	
	@Override
	public Collection<Table> readByNoOfSeats(int noOfSeats) throws Exception {
		PreparedStatement stmt =  buildReadTableByNoOfSeatsString(noOfSeats);
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
	private PreparedStatement buildReadTableByIsOutsideString(boolean isOutside) throws SQLException {
		String readTableByIsOutside = "SELECT * FROM DinnerTable WHERE isOutside = ?";
		PreparedStatement stmt = con.prepareStatement(readTableByIsOutside);
		stmt.setString(3, Boolean.toString(isOutside));
		System.out.println(readTableByIsOutside);
		return stmt;
	}
	
	@Override
	public Collection<Table> readByIsOutside(boolean isOutside) throws Exception {
		PreparedStatement stmt =  buildReadTableByIsOutsideString(isOutside);
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
	private PreparedStatement buildUpdateTableString() throws SQLException {
		String updateTableString = "";
		
		PreparedStatement stmt = con.prepareStatement(updateTableString);
		System.out.println(updateTableString);
		return stmt;
	}
	
	@Override
	public void update(Table obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// ******* DELETE *******
	private PreparedStatement buildDeleteTableString(Table table) throws SQLException {
		String deleteTableString = "DELETE * FROM DinnerTable WHERE tableNo = ?";
		PreparedStatement stmt = con.prepareStatement(deleteTableString);
		stmt.setString(1, Integer.toString(table.getTableNo()));
		System.out.println(deleteTableString);
		return stmt;
	}
	
	@Override
	public void delete(Table obj) throws Exception {
		PreparedStatement stmt = buildDeleteTableString(obj);
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