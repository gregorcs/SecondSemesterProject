	package dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoSupplyOrderIF;
import model.Item;
import model.LineItem;
import model.SupplyOrder;
import model.UrgencyEnum;

public class DaoSupplyOrderImplementation implements DaoSupplyOrderIF {

	Connection con = DBConnection.getInstance().getDBcon();


	private PreparedStatement buildCreateSupplyOrderStatement(SupplyOrder supplyOrder) throws SQLException {
		String query = "INSERT INTO SupplyOrder values (?, ?)";

		PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, supplyOrder.getDateString());
		stmt.setString(2, supplyOrder.getUrgencyEnum().toString());
		System.out.println(query);
		return stmt;
	}

	private PreparedStatement buildCreateSupplyOrderItemStatement(SupplyOrder supplyOrder, LineItem<Item> lineItem) throws SQLException {
		String query = "INSERT INTO SupplyOrder_Item values (?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, Integer.toString(supplyOrder.getSupplyOrderId()));
		stmt.setString(2, Integer.toString(lineItem.getItem().getItemId()));
		stmt.setString(3, Integer.toString(lineItem.getQuantity()));
		System.out.println(query);
		return stmt;
	}
	/*
	 * No time to implement before hand in
	private PreparedStatement buildReadAllSupplyOrdersStatement() throws SQLException {
		String query = "SELECT * FROM SupplyOrder";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}
	*/
	
	/*
	 * No time to implement before hand in
	private PreparedStatement buildReadAllItemsBySupplyOrderIdStatement(int id) throws SQLException {
		//TODO FINISH THIS
		String query = "SELECT * FROM SupplyOrder";
		PreparedStatement stmt = con.prepareStatement(query);
		System.out.println(query);
		return stmt;
	}
	*/
	private PreparedStatement buildReadSupplyOrderStatement(int id) throws SQLException {
		String query = "SELECT * FROM SupplyOrder WHERE supplyOrderId = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println(query);
		return stmt;
	}
	
	@Override
	public void create(SupplyOrder obj) throws Exception {
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
		          System.out.println("Rolling back database" + e);
		        } catch (SQLException excep) {
		          throw new SQLException("Error when rolling back database" + excep);
		        }
			}
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems" + e);
		} catch (Exception e) {
			throw new Exception("Technical error" + e);
		} finally {
			con.setAutoCommit(true);
			DBConnection.closeConnection();
		}
	}

	@Override
	public SupplyOrder read(int id) throws Exception {
		PreparedStatement stmt = buildReadSupplyOrderStatement(id);
		SupplyOrder supplyOrder = null;

		try {
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				//TODO ADD SOME ERROR HANDLING IN HERE/IN MODEL FOR PARSING THE STRING INTO THE DEPARTMENT ENUM
				supplyOrder = new SupplyOrder(rs.getInt(1), rs.getDate(2).toLocalDate(), UrgencyEnum.fromString(rs.getString(3)), new ArrayList<LineItem<Item>>());
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
		return supplyOrder;
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
		/*
		PreparedStatement stmt = buildReadAllSupplyOrdersStatement();
		ArrayList<SupplyOrder> itemsList = new ArrayList<>();

		try {
			con.setAutoCommit(false);
			ResultSet rs = stmt.executeQuery();

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
		*/
		return null;
	}

	private void createSupplyOrderItem(SupplyOrder supplyOrder, LineItem<Item> lineItem) throws SQLException, NullPointerException, Exception{
		PreparedStatement stmt = buildCreateSupplyOrderItemStatement(supplyOrder, lineItem);

		try {
			//should have checked updated rows here but no time :))
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new Exception("SQL exception");
		} catch (NullPointerException e) {
			throw new Exception("Null pointer exception, possible connection problems");
		} catch (Exception e) {
			throw new Exception("Technical error");
		} 
	}
}
