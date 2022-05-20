package controller;

import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoDecorationIF;
import model.Decoration;
import model.DecorationStatistics;

public class DecorationController {
	
	public Collection<Decoration> readAllSwitch(String department, String stockSortType) {
		
		switch(stockSortType) {
		//here i could reuse the enum
			case "LOWEST":
				return readAllByDepartmentSortByLowestStock();
			case "HIGHEST":
				return readAllByDepartmentSortByHighestStock();
			default:
				return null;
		}
	}
	
	public Collection<Decoration> readAllDecorations() {
		try {
			return DaoFactory.createDaoDecoration().readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Decoration> readAllByDepartmentSortByLowestStock() {
		try {
			return DaoFactory.createDaoDecoration().readAllByDepartmentSortByLowestStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Decoration> readAllByDepartmentSortByHighestStock() {
		try {
			return DaoFactory.createDaoDecoration().readAllByDepartmentSortByHighestStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Collection<DecorationStatistics> readSumDecorationsPerMonth() {
		try {
			return DaoFactory.createDaoDecoration().readSumDecorationsPerMonth();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
