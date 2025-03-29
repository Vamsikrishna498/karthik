package com.farmer.farmermanagement.enums;

public enum PortalAccess {
    ACTIVE,     // Fully active user with access to all functionalities
    INACTIVE,   // Temporarily deactivated user
    SUSPENDED,  // Suspended due to policy violations or non-compliance
    PENDING     // Awaiting admin approval for activation
}
