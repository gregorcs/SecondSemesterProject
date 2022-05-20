package dao.interfaces;

import java.util.Collection;

import model.Table;

public interface DaoTableIF extends DaoIF<Table> {
	public Collection<Table> readByNoOfSeats(int noOfSeats) throws Exception;
	public Collection<Table> readByIsOutside(boolean isOutside) throws Exception;
}
