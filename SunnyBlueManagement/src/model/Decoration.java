package model;

public class Decoration extends Item {

	private int decorationId;
	private int quantityInStock;

	public Decoration(int itemId, String name, String departmentType, int decorationId, int quantityInStock) {
		super(itemId, name, departmentType);
		this.decorationId = decorationId;
		this.quantityInStock = quantityInStock;
	}

	public Decoration(String name, String departmentType, int quantityInStock) {
		super(name, departmentType);
		this.quantityInStock = quantityInStock;
	}
	
	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public int getDecorationId() {
		return decorationId;
	}
}

