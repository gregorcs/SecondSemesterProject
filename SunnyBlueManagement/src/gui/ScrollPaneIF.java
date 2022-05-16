package gui;

import java.util.Collection;

import model.Item;

public interface ScrollPaneIF<T> {

	void initializeList();

	void updateListItem(Collection<T> listToShow);

	Item getSelectedItem();

	String getDepartmentFromChoice();

}