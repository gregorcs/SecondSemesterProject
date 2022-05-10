package dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoItemIF;
import model.Item;

public class DaoItemImplementation implements DaoItemIF {

	Connection con = DBConnection.getInstance().getDBcon();

	private PreparedStatement buildCreateString(Item item) throws SQLException {
		String createItemString = "INSERT INTO Item values (?, ?)";

		PreparedStatement stmt = con.prepareStatement(createItemString, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, item.getName());
		stmt.setString(2, item.getDepartmentType());
		System.out.println(createItemString);
		return stmt;
	}

	private PreparedStatement buildReadAllItemsString() throws SQLException {
		String readAllString = "SELECT * FROM Item";
		PreparedStatement stmt = con.prepareStatement(readAllString);
		System.out.println(readAllString);
		return stmt;
	}
	
	private PreparedStatement buildReadItemString(int itemId) throws SQLException {
		String readItemString = "SELECT * FROM Item WHERE itemId = ?";
		PreparedStatement stmt = con.prepareStatement(readItemString);
		stmt.setString(1, Integer.toString(itemId));
		System.out.println(readItemString);
		return stmt;
	}
	
	private PreparedStatement buildDeleteItemString(Item item) throws SQLException {
		String deleteItemString = "DELETE FROM Item WHERE itemId = ?";
		PreparedStatement stmt = con.prepareStatement(deleteItemString);
		stmt.setString(1, Integer.toString(item.getItemId()));
		System.out.println(deleteItemString);
		return stmt;

	}
	

	@Override
	public void create(Item obj) throws Exception {
		PreparedStatement stmt = buildCreateString(obj);

		try {
			stmt.executeQuery();

		} catch (SQLException e) {
			throw new Exception("SQL exception");
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			throw new Exception("Technical error");
		} finally {
			DBConnection.closeConnection();
		}

	}

	@Override
	public Item read(int id) throws Exception {
		PreparedStatement stmt = buildReadItemString(id);
		Item item = new Item();

		try {
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {
			throw new Exception("SQL exception");
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			throw new Exception("Technical error");
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
		//TODO
		PreparedStatement stmt = buildDeleteItemString(obj);
		try {
			stmt.executeQuery();

		} catch (SQLException e) {
			throw new Exception("SQL exception");
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			throw new Exception("Technical error");
		} finally {
			DBConnection.closeConnection();
		}

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
			throw new Exception("SQL exception");
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			throw new Exception("Technical error");
		} finally {
			DBConnection.closeConnection();
		}
		return itemsList;
	}

}
