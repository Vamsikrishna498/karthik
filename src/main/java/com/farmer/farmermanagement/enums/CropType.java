package com.farmer.farmermanagement.enums;

public enum CropType {
    FRUITS("Fruits"),
    VEGETABLES("Vegetables"),
    GRAINS("Grains"),
    PULSES("Pulses"),
    OIL_SEEDS("Oil Seeds"),
    COTTON("Cotton");

    private final String displayName;

    CropType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static CropType fromString(String text) {
        for (CropType crop : CropType.values()) {
            if (crop.name().equalsIgnoreCase(text) || crop.displayName.equalsIgnoreCase(text)) {
                return crop;
            }
        }
        throw new IllegalArgumentException("Invalid crop type: " + text);
    }
}

