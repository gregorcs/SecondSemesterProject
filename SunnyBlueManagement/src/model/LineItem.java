package model;

public class LineItem<T> {
	private int quantity;
	private T item;
	
	public LineItem(int quantity, T item) {
		super();
		this.quantity = quantity;
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
}
