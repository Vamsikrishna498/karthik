package com.farmer.farmermanagement.enums;

public enum Document {
    VOTER_CARD("Voter Card"),
    AADHAR_NUMBER("Aadhar Number"),
    PAN_NUMBER("PAN Number"),
    PPB_NUMBER("PPB Number");

    private final String displayName;

    Document(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Document fromString(String text) {
        for (Document doc : Document.values()) {
            if (doc.name().equalsIgnoreCase(text) || doc.displayName.equalsIgnoreCase(text)) {
                return doc;
            }
        }
        throw new IllegalArgumentException("Invalid document type: " + text);
    }
}
