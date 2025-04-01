package com.farmer.farmermanagement.enums;

public enum PortalRole {
    FARMER("Farmer"),
    LAND_OWNER("Land Owner"),
    AGRI_VENDOR("Agricultural Vendor"),
    BANK_OFFICER("Bank Officer"),
    GOVERNMENT_OFFICER("Government Officer"),
    ADMIN("Administrator"),
    USER("User");

    private final String displayName;

    PortalRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PortalRole fromString(String text) {
        for (PortalRole role : PortalRole.values()) {
            if (role.name().equalsIgnoreCase(text) || role.displayName.equalsIgnoreCase(text)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid portal role: " + text);
    }
}
