package controller;

import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoDecorationIF;
import model.Decoration;
import model.DecorationStatistics;

public class DecorationController {

	private DaoDecorationIF daoDecoration;
	
	public DecorationController() {
		this.daoDecoration = DaoFactory.createDaoDecoration();
	}
	
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
	
	
	public void deleteDecoration(Decoration decoration) {
		this.daoDecoration = DaoFactory.createDaoDecoration();

		try {
			daoDecoration.delete(decoration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Collection<Decoration> readAllDecorations() {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		try {
			return daoDecoration.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Decoration> readAllByDepartmentSortByLowestStock() {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		try {
			return daoDecoration.readAllByDepartmentSortByLowestStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Decoration> readAllByDepartmentSortByHighestStock() {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		try {
			return daoDecoration.readAllByDepartmentSortByHighestStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Collection<DecorationStatistics> readSumDecorationsPerMonth() {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		try {
			return daoDecoration.readSumDecorationsPerMonth();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
