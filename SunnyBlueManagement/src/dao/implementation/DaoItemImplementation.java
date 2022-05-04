package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoItemIF;
import model.Item;

public class DaoItemImplementation implements DaoItemIF {
	
	Connection con = DBConnection.getInstance().getDBcon();

	private PreparedStatement buildCreateString(Item item) throws SQLException {
		String createItemString = "INSERT INTO Item values (?, ?)";
		
		
		PreparedStatement stmt = con.prepareStatement(createItemString, PreparedStatement.RETURN_GENERATED_KEYS);		
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
	
	/**
	 * TODO return more type of exceptions here
	 */
	@Override
	public int create(Item obj) throws Exception {
		PreparedStatement stmt = buildCreateString(obj);
		int insertedKey = 1;
		
		try {
			ResultSet rs = stmt.executeQuery();
			//TODO RETURN THE CREATED SUPPLY ORDER

		} catch (SQLException e) {
			insertedKey = -1;
			throw new Exception("SQL exception");
		} catch (NullPointerException e) {
			insertedKey = -2;
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			insertedKey = -3;
			throw new Exception("Technical error");
		} finally {
			DBConnection.closeConnection();
		}
		
		return insertedKey;
	}

	@Override
	public Item read(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Item obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Item obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Item> readAll() throws Exception {
		PreparedStatement stmt = buildReadAllItemsString();
		ArrayList<Item> itemsList = new ArrayList<Item>();
		int insertedKey = 0;
		
		try {
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				itemsList.add(new Item(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
		} catch (SQLException e) {
			insertedKey = -1;
			throw new Exception("SQL exception");
		} catch (NullPointerException e) {
			insertedKey = -2;
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			insertedKey = -3;
			throw new Exception("Technical error");
		} finally {
			DBConnection.closeConnection();
		}
		return itemsList;
	}

}
