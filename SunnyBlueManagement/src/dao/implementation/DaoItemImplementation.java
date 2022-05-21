package dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoItemIF;
import model.Item;

public class DaoItemImplementation implements DaoItemIF {

	Connection con = DBConnection.getInstance().getDBcon();

	// ******* CREATE *******
	private PreparedStatement buildCreateStatement(Item item) throws SQLException {
		String query = "INSERT INTO Item values (?, ?)";

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, item.getName());
		stmt.setString(2, item.getDepartmentType());
		System.out.println(query);
		return stmt;
	}

	private PreparedStatement buildReadItemStatement(int itemId) throws SQLException {
		String query = "SELECT * FROM Item WHERE itemId = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, itemId);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildDeleteItemStatement(Item item) throws SQLException {
		String query = "UPDATE SupplyOrder_Item "
				+ "SET item_itemId_FK = NULL "
				+ "WHERE item_itemId_FK = ?; "
				+ "DELETE FROM Item WHERE itemId = ?;";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, item.getItemId());
		stmt.setInt(2, item.getItemId());
		System.out.println(query);
		return stmt;

	}
	
	private PreparedStatement buildReadByNameItemStatement(String name) throws SQLException {
		String query = "SELECT * FROM Item WHERE name LIKE  ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "%" + name + "%");
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadByNameAndDepartmentStatement(String name, String department) throws SQLException {
		String query = "SELECT * FROM Item WHERE name LIKE ? AND department = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "%" + name + "%");
		stmt.setString(2, department);
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadByNameSortByIdASCStatement(String name) throws SQLException {
		String query = "SELECT * FROM Item WHERE name LIKE ? ORDER BY itemId ASC";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "%" + name + "%");
		System.out.println(query);
		return stmt;
	}
	
	private PreparedStatement buildReadByNameSortByIdDESCStatement(String name) throws SQLException {
		String query = "SELECT * FROM Item WHERE name LIKE ? ORDER BY itemId DESC";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "%" + name + "%");
		System.out.println(query);
		return stmt;
	}
  
	//would have been nice to return the generated key or bool value here
	@Override
	public void create(Item obj) throws Exception {
		PreparedStatement stmt = buildCreateStatement(obj);

		try {
			int insertedRows = stmt.executeUpdate();
			
			if(insertedRows != 1) {
				throw new Exception("Item was not created");
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
	}
	
	@Override
	public Item read(int id) throws Exception {
		PreparedStatement stmt = buildReadItemStatement(id);
		Item item = new Item();

		try {
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				//TODO ADD SOME ERROR HANDLING IN HERE/IN MODEL FOR PARSING THE STRING INTO THE DEPARTMENT ENUM
				item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3));
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
		return item;
	}


	@Override
	public void update(Item obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Item obj) throws Exception {
		PreparedStatement stmt = buildDeleteItemStatement(obj);
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

	// ******* READ ALL*******
	//this will be duplicate code with daoDecoration, no time to make a shared class for queries rn
	private PreparedStatement buildReadAllItemsString() throws SQLException {
		String readAllString = "SELECT * FROM Item";
		PreparedStatement stmt = con.prepareStatement(readAllString);
		System.out.println(readAllString);
		return stmt;
	}
	
	@Override
	public Collection<Item> readAll() throws Exception {
		PreparedStatement stmt = buildReadAllItemsString();
		ArrayList<Item> itemsList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itemsList.add(new Item(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
		return itemsList;
	}

	@Override
	public Collection<Item> readByName(String name) throws Exception {
		PreparedStatement stmt = buildReadByNameItemStatement(name);
		ArrayList<Item> itemsList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itemsList.add(new Item(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
		return itemsList;
	}

	@Override
	public Collection<Item> readByNameAndDepartment(String name, String departmentEnum) throws Exception {
		PreparedStatement stmt = buildReadByNameAndDepartmentStatement(name, departmentEnum);
		ArrayList<Item> itemsList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itemsList.add(new Item(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
		return itemsList;
	}

	@Override
	public Collection<Item> readByNameSortByIdASC(String name) throws Exception {
		PreparedStatement stmt = buildReadByNameSortByIdASCStatement(name);
		ArrayList<Item> itemsList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itemsList.add(new Item(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
		return itemsList;
	}
	
	@Override
	public Collection<Item> readByNameSortByIdDESC(String name) throws Exception {
		PreparedStatement stmt = buildReadByNameSortByIdDESCStatement(name);
		ArrayList<Item> itemsList = new ArrayList<>();

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				itemsList.add(new Item(rs.getInt(1), rs.getString(2), rs.getString(3)));
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
		return itemsList;
	}
}