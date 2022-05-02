package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoSupplyOrderIF;
import model.SupplyOrder;

public class DaoSupplyOrderImplementation implements DaoSupplyOrderIF {
	
	Connection con = DBConnection.getInstance().getDBcon();

	
	public PreparedStatement buildCreateStatement(SupplyOrder supplyOrder) throws SQLException {
		String createSupplyOrder = "INSERT INTO SupplyOrder values (?, ?)";
		
		PreparedStatement stmt = con.prepareStatement(createSupplyOrder, PreparedStatement.RETURN_GENERATED_KEYS);		
		stmt.setString(1, supplyOrder.getDateString());
		stmt.setString(2, supplyOrder.getUrgency());
		return stmt;
	}
	
	@Override
	public SupplyOrder create(SupplyOrder obj) throws Exception {
		
		PreparedStatement stmt = buildCreateStatement(obj);
		SupplyOrder createdSupplyOrder = new SupplyOrder();

		int insertedKey = 1;
		
		try {
			ResultSet rs = stmt.executeQuery();
			
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
		
		return createdSupplyOrder;
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
	

}
