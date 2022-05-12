package model;

public class Item {
	
	private int itemId;
	private String name;
	private String departmentType;
	
	public Item(int itemId, String name, String departmentType) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.departmentType = departmentType;
	}

	public Item(String name, String departmentType) {
		super();
		this.name = name;
		this.departmentType = departmentType;
	}
	
	public Item() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmentType() {
		return departmentType;
	}
	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
