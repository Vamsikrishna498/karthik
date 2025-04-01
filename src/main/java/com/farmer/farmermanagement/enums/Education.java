package com.farmer.farmermanagement.enums;

public enum Education {
    PRIMARY_SCHOOLING("Primary Schooling"),
    HIGH_SCHOOL("High School"),
    INTERMEDIATE("Intermediate"),
    DEGREE("Degree"),
    GRADUATE("Graduate"),
    POSTGRADUATE("Postgraduate");

    private final String displayName;

    Education(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Education fromString(String text) {
        for (Education edu : Education.values()) {
            if (edu.name().equalsIgnoreCase(text) || edu.displayName.equalsIgnoreCase(text)) {
                return edu;
            }
        }
        throw new IllegalArgumentException("Invalid education type: " + text);
    }
}
