package model;

public enum DepartmentEnum {
    RESTAURANT("RESTAURANT"),
    KITCHEN("KITCHEN");
	
    private String name;

    DepartmentEnum(String text) {
        this.name = text;
    }

	public String getText() {
        return this.name;
    }
	
	/**
	 * used for finding out what enum the input text contains, regardless of case
	 * @param text
	 * @return
	 */
	public static DepartmentEnum fromString(String text) {
	    for (DepartmentEnum tempEnum : DepartmentEnum.values()) {
	        if (tempEnum.name.equalsIgnoreCase(text)) {
	            return tempEnum;
	        }
	    }
	    throw new IllegalArgumentException("No constant with text " + text + " found");
	}
}
