package com.farmer.farmermanagement.enums;

public enum BankName {
    HDFC_BANK("HDFC Bank"),
    SBI("State Bank of India"),
    ICICI_BANK("ICICI Bank"),
    AXIS_BANK("Axis Bank"),
    PNB("Punjab National Bank"),
    BOI("Bank of India"),
    OTHER("Other");

    private final String displayName;

    BankName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static BankName fromString(String text) {
        for (BankName bank : BankName.values()) {
            if (bank.name().equalsIgnoreCase(text) || bank.displayName.equalsIgnoreCase(text)) {
                return bank;
            }
        }
        return OTHER;
    }
}
