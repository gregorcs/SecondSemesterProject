package dao.interfaces;

import java.util.Collection;

import model.Decoration;
import model.DecorationStatistics;

public interface DaoDecorationIF extends DaoIF<Decoration> {

	public Collection<Decoration> readAllByDepartmentSortByHighestStock() throws Exception;
	public Collection<Decoration> readAllByDepartmentSortByLowestStock() throws Exception;
	public Collection<DecorationStatistics> readSumDecorationsPerMonth() throws Exception;
}
