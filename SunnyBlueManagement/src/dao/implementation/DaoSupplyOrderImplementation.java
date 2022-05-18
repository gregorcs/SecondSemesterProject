	package dao.implementation;

import java.sql.*;
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

		PreparedStatement stmt = con.prepareStatement(createSupplyOrder, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, supplyOrder.getDateString());
		stmt.setString(2, supplyOrder.getUrgencyEnum().toString());
		System.out.println(createSupplyOrder);
		return stmt;
	}

	private PreparedStatement buildCreateSupplyOrderItemStatement(SupplyOrder supplyOrder, LineItem<Item> lineItem) throws SQLException {
		String createSupplyOrderItem = "INSERT INTO SupplyOrder_Item values (?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(createSupplyOrderItem, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, Integer.toString(supplyOrder.getSupplyOrderId()));
		stmt.setString(2, Integer.toString(lineItem.getItem().getItemId()));
		stmt.setString(3, Integer.toString(lineItem.getQuantity()));
		System.out.println(createSupplyOrderItem);
		return stmt;
	}

	@Override
	public void create(SupplyOrder obj) throws Exception {
		con = DBConnection.getInstance().getDBcon();
		PreparedStatement stmt = buildCreateSupplyOrderStatement(obj);

		try {
		    con.setAutoCommit(false);
			stmt.executeUpdate();

	        ResultSet generatedKeys = stmt.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            obj.setSupplyOrderId(generatedKeys.getInt(1));
	        }

			for (LineItem<Item> lineItem : obj.getListOfItems()) {
				createSupplyOrderItem(obj, lineItem);
			}
			con.commit();

		} catch (SQLException e) {
			if (con != null) {
		        try {
		          con.rollback();
		          System.out.println("Rolling back database");
		        } catch (SQLException excep) {
		          throw new SQLException("Error when rolling back database");
		        }
			}
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			throw new Exception("Technical error");
		} finally {
			DBConnection.closeConnection();
		}
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

	private void createSupplyOrderItem(SupplyOrder supplyOrder, LineItem<Item> lineItem) throws SQLException, NullPointerException, Exception{

		PreparedStatement stmt = buildCreateSupplyOrderItemStatement(supplyOrder, lineItem);

		try {
			stmt.executeQuery();

		} catch (SQLException e) {
			throw new Exception("SQL exception");
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			throw new Exception("Technical error");
		} finally {
			//TODO REMOVED CLOSE.CONNECTION FROM HERE 
		}

	}


}
