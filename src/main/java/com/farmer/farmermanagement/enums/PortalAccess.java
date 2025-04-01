package com.farmer.farmermanagement.enums;

public enum PortalAccess {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended"),
    PENDING("Pending");

    private final String displayName;

    PortalAccess(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PortalAccess fromString(String text) {
        for (PortalAccess access : PortalAccess.values()) {
            if (access.name().equalsIgnoreCase(text) || access.displayName.equalsIgnoreCase(text)) {
                return access;
            }
        }
        throw new IllegalArgumentException("Invalid portal access status: " + text);
    }
}

