package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoDecorationIF;
import model.Decoration;
import model.DecorationStatistics;
import model.LineItem;

public class DaoDecorationImplementation implements DaoDecorationIF{

	Connection con = DBConnection.getInstance().getDBcon();

	
	private PreparedStatement buildReadAllItemsString() throws SQLException {
		String query = "SELECT t1.itemId, t2.decorationId, t1.name, t1.department, t2.quantityInStock "
				+ "FROM Item t1 "
				+ "LEFT JOIN Decoration t2 "
				+ "ON t1.itemId = t2.item_itemId_FK";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadAllByDepartmentSortByAsc() throws SQLException {
		String query = "SELECT t1.itemId, t2.decorationId, t1.name, t1.department, t2.quantityInStock "
				+ "FROM Item t1 "
				+ "LEFT JOIN Decoration t2 "
				+ "ON t1.itemId = t2.item_itemId_FK "
				+ "ORDER BY "
				+ "t2.quantityInStock ASC";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadAllByDepartmentSortByDesc() throws SQLException {
		String query = "SELECT t1.itemId, t2.decorationId, t1.name, t1.department, t2.quantityInStock "
				+ "FROM Item t1 "
				+ "LEFT JOIN Decoration t2 "
				+ "ON t1.itemId = t2.item_itemId_FK "
				+ "ORDER BY "
				+ "t2.quantityInStock DESC";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadSumDecorationsPerMonth() throws SQLException {
		String query = "SELECT MONTH(CONVERT(DATETIME, t2.date, 103)) 'month', SUM(t1.quantity) 'sum' "
				+ "FROM Reservation_Decoration t1 "
				+ "INNER JOIN Reservation t2 "
				+ "ON t1.reservation_reservationId_FK = t2.reservationId "
				+ "GROUP BY MONTH(CONVERT(DATETIME, t2.date, 103))";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}
	
	
	//NORBERT DID CHANGES HERE
	private PreparedStatement buildReadById(int id) throws SQLException {
		String query = "SELECT name, department FROM Item INNER JOIN (SELECT item_itemId_FK FROM Decoration WHERE decorationId = ?) DecorationItem ON Item.itemId = DecorationItem.item_itemId_FK";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println(query);
		return stmt;
	}
	
	@Override
	public void create(Decoration obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//NORBERT DID CHANGES HERE
	@Override
	public Decoration read(int id) throws Exception {
		PreparedStatement stmt = buildReadById(id);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			return new Decoration(rs.getString(1), rs.getString(2), 0); //THE 0 IS THERE BECAUSE I DIDNT WANT TO MAKE A NEW CONSTRUCTOR
		}
		return null;
	}


	@Override
	public void update(Decoration obj) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Decoration obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Decoration> readAll() throws Exception {
			PreparedStatement stmt = buildReadAllItemsString();
			ArrayList<Decoration> decorationsList = new ArrayList<>();

			try {
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					//check if the Item-Decoration join has a decoration id
					if (rs.getInt(2) != 0) {
						decorationsList.add(new Decoration(rs.getInt(1), rs.getString(3), rs.getString(4), 
								rs.getInt(2), rs.getInt(5)));					
						} 
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
			return decorationsList;
	}
	
	@Override
	public Collection<Decoration> readAllByDepartmentSortByLowestStock() throws Exception {
		PreparedStatement stmt = buildReadAllByDepartmentSortByAsc();
		ArrayList<Decoration> decorationsList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				//check if the Item-Decoration join has a decoration id
				if (rs.getInt(2) != 0) {
					decorationsList.add(new Decoration(rs.getInt(1), rs.getString(3), rs.getString(4), 
							rs.getInt(2), rs.getInt(5)));					
					} 
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
		return decorationsList;
	}
	
	@Override
	public Collection<Decoration> readAllByDepartmentSortByHighestStock() throws Exception {
		PreparedStatement stmt = buildReadAllByDepartmentSortByDesc();
		ArrayList<Decoration> decorationsList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				//check if the Item-Decoration join has a decoration id CAN BE DONE IN SQL, REDO
				if (rs.getInt(2) != 0) {
					decorationsList.add(new Decoration(rs.getInt(1), rs.getString(3), rs.getString(4), 
							rs.getInt(2), rs.getInt(5)));					
					} 
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
		return decorationsList;
	}

	@Override
	public Collection<DecorationStatistics> readSumDecorationsPerMonth() throws Exception {
		PreparedStatement stmt = buildReadSumDecorationsPerMonth();
		ArrayList<DecorationStatistics> decorationsList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				decorationsList.add(new DecorationStatistics(rs.getInt(1), rs.getInt(2)));					
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
		return decorationsList;
	}
}
