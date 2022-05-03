package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoSupplyOrderIF;
import model.Item;
import model.LineItem;
import model.SupplyOrder;

public class DaoSupplyOrderImplementation implements DaoSupplyOrderIF {
	
	Connection con = DBConnection.getInstance().getDBcon();

	
	private PreparedStatement buildCreateSupplyOrderStatement(SupplyOrder supplyOrder) throws SQLException {
		String createSupplyOrder = "INSERT INTO SupplyOrder values (?, ?)";
		
		PreparedStatement stmt = con.prepareStatement(createSupplyOrder, PreparedStatement.RETURN_GENERATED_KEYS);		
		stmt.setString(1, supplyOrder.getDateString());
		stmt.setString(2, supplyOrder.getUrgency());
		System.out.println(createSupplyOrder);
		return stmt;
	}
	
	private PreparedStatement buildCreateSupplyOrderItemStatement(SupplyOrder supplyOrder, LineItem lineItem) throws SQLException {
		String createSupplyOrderItem = "INSERT INTO SupplyOrder_Item values (?, ?, ?)";
		System.out.println("order id: " + supplyOrder.getSupplyOrderId() + " itemId: " + lineItem.getItem().getItemId() + " quantity: " + lineItem.getQuantity());
		PreparedStatement stmt = con.prepareStatement(createSupplyOrderItem, PreparedStatement.RETURN_GENERATED_KEYS);		
		stmt.setString(1, Integer.toString(supplyOrder.getSupplyOrderId()));
		stmt.setString(2, Integer.toString(lineItem.getItem().getItemId()));
		stmt.setString(3, Integer.toString(lineItem.getQuantity()));
		System.out.println(createSupplyOrderItem);
		return stmt;
	}
	
	@Override
	public int create(SupplyOrder obj) throws Exception {
		
		PreparedStatement stmt = buildCreateSupplyOrderStatement(obj);
		int insertedKey = 1;
		
		try {
			stmt.executeUpdate();
			//TODO RETURN THE CREATED SUPPLY ORDER
		
	        ResultSet generatedKeys = stmt.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            obj.setSupplyOrderId(generatedKeys.getInt(1));
	        }
			
			for (LineItem lineItem : obj.getListOfItems()) {
				createSupplyOrderItem(obj, lineItem);
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
		
		return insertedKey;
	}

	@Override
	public SupplyOrder read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SupplyOrder obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SupplyOrder obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<SupplyOrder> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int createSupplyOrderItem(SupplyOrder supplyOrder, LineItem lineItem) throws SQLException, Exception{
		
		PreparedStatement stmt = buildCreateSupplyOrderItemStatement(supplyOrder, lineItem);
		int insertedKey = 1;
		
		try {
			ResultSet rs = stmt.executeQuery();
			//TODO RETURN THE CREATED SUPPLY ORDER

		} catch (SQLException e) {
			insertedKey = -1;
			throw new SQLException("SQL exception");
		} catch (NullPointerException e) {
			insertedKey = -2;
			throw new NullPointerException("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			insertedKey = -3;
			throw new Exception("Technical error");
		} finally {
			//TODO REMOVED CLOSE.CONNECTION FROM HERE TO TEST IF TRANSACTION WORKS
		}
		
		return insertedKey;
	}
	

}
