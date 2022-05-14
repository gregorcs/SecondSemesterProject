package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import dao.DBConnection;
import dao.interfaces.DaoTableIF;
import model.Table;

public class DaoTableImplementation implements DaoTableIF {
	Connection con = DBConnection.getInstance().getDBcon();

	private PreparedStatement buildCreateString(Table table) throws SQLException {
		String createTableString = "INSERT INTO Table values (?, ?, ?)";
		
		PreparedStatement stmt = con.prepareStatement(createTableString, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, table.getTableNo());
		stmt.setInt(2, table.getNoOfSeats());
		stmt.setBoolean(3, table.getIsOutside());
		System.out.println(createTableString);
		return stmt;
	}

	private PreparedStatement buildReadAllTablesString() throws SQLException {
		String readAllString = "SELECT * FROM Table";
		PreparedStatement stmt = con.prepareStatement(readAllString);
		System.out.println(readAllString);
		return stmt;
	}

	
	@Override
	public void create(Table obj) throws Exception {
		PreparedStatement stmt = buildCreateString(obj);
	}

	@Override
	public Table read(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Table obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Table obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	
	public Collection<Table> readAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}