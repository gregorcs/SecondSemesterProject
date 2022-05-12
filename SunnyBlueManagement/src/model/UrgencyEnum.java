package model;

public enum UrgencyEnum {
	LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");
	
    private String name;

    UrgencyEnum(String text) {
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
	public static UrgencyEnum fromString(String text) {
	    for (UrgencyEnum tempEnum : UrgencyEnum.values()) {
	        if (tempEnum.name.equalsIgnoreCase(text)) {
	            return tempEnum;
	        }
	    }
	    throw new IllegalArgumentException("No constant with text " + text + " found");
	}
}
