package controller;

import java.util.ArrayList;
import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoItemIF;
import model.Decoration;

public class DecorationController {

	private DaoItemIF daoItem;
	
	public DecorationController() {
		daoItem = DaoFactory.createDaoItem();
	}
	
	public Collection<Decoration> readAllDecorations() {
		try {
			return daoItem.readAllDecorations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Decoration>();
	}
}
