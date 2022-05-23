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
import model.Item;

public class DaoDecorationImplementation implements DaoDecorationIF{

	Connection con = DBConnection.getInstance().getDBcon();

	//i dont like making this duplicate, but we would need the common data source class for them (or make all create methods return generated keys) :(
	private PreparedStatement buildCreateItemStatement(Item item) throws SQLException {
		String query = "INSERT INTO Item values (?, ?)";

		PreparedStatement stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, item.getName());
		stmt.setString(2, item.getDepartmentType());
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildCreateDecorationStatement(Decoration decoration, int createdItemId) throws SQLException {
		String query = "INSERT INTO Decoration values (?, ?)";

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, createdItemId);
		stmt.setInt(2, decoration.getQuantityInStock());
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildUpdateItemStatement(Decoration decoration) throws SQLException {
		String query = "UPDATE Item "
				+ "SET name = ?, department = ? "
				+ "WHERE itemId = ?;";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, decoration.getName());
		stmt.setString(2, decoration.getDepartmentType());
		stmt.setInt(3, decoration.getItemId());
		return stmt;
	}
	
	private PreparedStatement buildUpdateDecorationStatement(Decoration decoration) throws SQLException {
		String query = "UPDATE Decoration "
				+ "SET quantityInStock = ? "
				+ "WHERE decorationId = ?;";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, decoration.getQuantityInStock());
		stmt.setInt(2, decoration.getDecorationId());
		return stmt;
	}
	
	private PreparedStatement buildReadAllDecorationsStringStatement() throws SQLException {
		String query = "SELECT t1.itemId, t2.decorationId, t1.name, t1.department, t2.quantityInStock "
				+ "FROM Item t1 "
				+ "LEFT JOIN Decoration t2 "
				+ "ON t1.itemId = t2.item_itemId_FK";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadAllByDepartmentSortByAscStatement() throws SQLException {
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
	
	private PreparedStatement buildReadAllByDepartmentSortByDescStatement() throws SQLException {
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
	
	private PreparedStatement buildReadSumDecorationsPerMonthStatement() throws SQLException {
		String query = "SELECT MONTH(CONVERT(DATETIME, t2.date, 103)) 'month', SUM(t1.quantity) 'sum' "
				+ "FROM Reservation_Decoration t1 "
				+ "INNER JOIN Reservation t2 "
				+ "ON t1.reservation_reservationId_FK = t2.reservationId "
				+ "GROUP BY MONTH(CONVERT(DATETIME, t2.date, 103))";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}
	

	private PreparedStatement buildDeleteDecorationStatement(Decoration decoration) throws SQLException {
		String query = "UPDATE Reservation_Decoration "
				+ "SET decoration_decorationId_FK = NULL "
				+ "WHERE decoration_decorationId_FK = ?; "
				+ "DELETE FROM Decoration WHERE decorationId = ?;";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, decoration.getDecorationId());
		stmt.setInt(2, decoration.getDecorationId());
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
		PreparedStatement stmtItem = buildCreateItemStatement(new Item(obj.getName(), obj.getDepartmentType()));
		PreparedStatement stmtDecoration;
		int generatedItemId = 0;
		try {
			con.setAutoCommit(false);
			int rowsUpdated = stmtItem.executeUpdate();
	        ResultSet generatedKeys = stmtItem.getGeneratedKeys();
	        //gotta check if the parent class actually went through before creating decoration :)
	        if (generatedKeys.next() && rowsUpdated == 1) {
	        	generatedItemId = generatedKeys.getInt(1);
				stmtDecoration = buildCreateDecorationStatement(obj, generatedItemId);
				//TODO check for updated rows here too
				stmtDecoration.executeUpdate();
	        } else {
	        	throw new SQLException("Item was not created properly before decoration");
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
		PreparedStatement stmtItem = buildUpdateItemStatement(obj);
		PreparedStatement stmtDecoration = buildUpdateDecorationStatement(obj);
		
		try {
			con.setAutoCommit(false);
			int rowsUpdated = stmtItem.executeUpdate();
	        //gotta check if the parent class actually went through before updating decoration :)
	        if (rowsUpdated == 1) {
				//TODO check for updated decorations rows 
	        	stmtDecoration.executeUpdate();
	        } else {
	        	throw new SQLException("Item was not created properly before decoration");
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
	public void delete(Decoration obj) throws Exception {
		PreparedStatement stmt = buildDeleteDecorationStatement(obj);
		
		try {
			stmt.executeUpdate();
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

	@Override
	public Collection<Decoration> readAll() throws Exception {
			PreparedStatement stmt = buildReadAllDecorationsStringStatement();
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
		PreparedStatement stmt = buildReadAllByDepartmentSortByAscStatement();
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
		PreparedStatement stmt = buildReadAllByDepartmentSortByDescStatement();
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
		PreparedStatement stmt = buildReadSumDecorationsPerMonthStatement();
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
