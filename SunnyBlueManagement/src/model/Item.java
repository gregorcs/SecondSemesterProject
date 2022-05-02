package model;

public class Item {
	
	private String name;
	private int quantityInStock;
	private String departmentType;
	
	public Item(String name, int quantityInStock, String departmentType) {
		super();
		this.name = name;
		this.quantityInStock = quantityInStock;
		this.departmentType = departmentType;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public String getDepartmentType() {
		return departmentType;
	}
	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}
}
