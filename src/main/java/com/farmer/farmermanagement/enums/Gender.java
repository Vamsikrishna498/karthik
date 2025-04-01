package com.farmer.farmermanagement.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    TRANSGENDER("Transgender");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Gender fromString(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(text) || gender.displayName.equalsIgnoreCase(text)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender type: " + text);
    }
}
