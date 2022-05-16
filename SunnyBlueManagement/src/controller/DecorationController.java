package controller;

import java.util.ArrayList;
import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoDecorationIF;
import model.Decoration;

public class DecorationController {

	private DaoDecorationIF daoDecoration;
	
	public DecorationController() {
		daoDecoration = DaoFactory.createDaoDecoration();
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
	
	public Collection<Decoration> readAllDecorations() {
		try {
			return daoDecoration.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Decoration> readAllByDepartmentSortByLowestStock() {
		try {
			return daoDecoration.readAllByDepartmentSortByLowestStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Decoration> readAllByDepartmentSortByHighestStock() {
		try {
			return daoDecoration.readAllByDepartmentSortByHighestStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
