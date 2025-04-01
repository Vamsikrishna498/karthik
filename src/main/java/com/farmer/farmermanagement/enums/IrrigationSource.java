package com.farmer.farmermanagement.enums;

public enum IrrigationSource {
    CANAL("Canal"),
    OPEN_WELL("Open Well"),
    BORE_WELL("Bore Well"),
    TANK("Tank"),
    RIVER("River"),
    DRIP("Drip"),
    OTHER("Other");

    private final String displayName;

    IrrigationSource(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static IrrigationSource fromString(String text) {
        for (IrrigationSource source : IrrigationSource.values()) {
            if (source.name().equalsIgnoreCase(text) || source.displayName.equalsIgnoreCase(text)) {
                return source;
            }
        }
        throw new IllegalArgumentException("Invalid irrigation source: " + text);
    }
}

