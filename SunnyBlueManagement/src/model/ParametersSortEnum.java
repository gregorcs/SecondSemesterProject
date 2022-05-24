package model;

public enum ParametersSortEnum {
	LOWEST("LOWEST"),
    HIGHEST("HIGHEST");
	
    private String name;

    ParametersSortEnum(String text) {
        this.name = text;
    }

	public String getText() {
        return this.name;
    }
	
	/**
	 * used for finding out what enum the input text contains (basically searching for an enum in a string), regardless of case
	 * @param text
	 * @return
	 */
	public static ParametersSortEnum fromString(String text) {
	    for (ParametersSortEnum tempEnum : ParametersSortEnum.values()) {
	        if (tempEnum.name.equalsIgnoreCase(text)) {
	            return tempEnum;
	        }
	    }
	    throw new IllegalArgumentException("No constant with text " + text + " found");
	}
}
