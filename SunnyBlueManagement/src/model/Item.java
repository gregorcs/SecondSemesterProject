package model;

public class Item {
	
	private int itemId;
	private String name;
	private DepartmentEnum departmentType;
	
	public Item(int itemId, String name, DepartmentEnum departmentType) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.departmentType = departmentType;
	}

	public Item(String name, DepartmentEnum departmentType) {
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
	public DepartmentEnum getDepartmentType() {
		return departmentType;
	}
	public void setDepartmentType(DepartmentEnum departmentType) {
		this.departmentType = departmentType;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
