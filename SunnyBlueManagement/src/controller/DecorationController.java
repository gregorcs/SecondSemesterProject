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
	
	public void createDecoration(String name, String department, int quantityInStock) {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		Decoration decorationToCreate = new Decoration(name, department, quantityInStock);
		try {
			daoDecoration.create(decorationToCreate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDecoration(Decoration decorationToUpdate, String updatedName, String updatedDepartment, int updatedQuantityInStock) {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		decorationToUpdate.setName(updatedName);
		decorationToUpdate.setDepartmentType(updatedDepartment);
		decorationToUpdate.setQuantityInStock(updatedQuantityInStock);
		try {
			daoDecoration.update(decorationToUpdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			return DaoFactory.createDaoDecoration().readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Decoration> readAllByDepartmentSortByLowestStock() {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		try {
			return DaoFactory.createDaoDecoration().readAllByDepartmentSortByLowestStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Decoration> readAllByDepartmentSortByHighestStock() {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		try {
			return DaoFactory.createDaoDecoration().readAllByDepartmentSortByHighestStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Collection<DecorationStatistics> readSumDecorationsPerMonth() {
		this.daoDecoration = DaoFactory.createDaoDecoration();
		try {
			return DaoFactory.createDaoDecoration().readSumDecorationsPerMonth();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
