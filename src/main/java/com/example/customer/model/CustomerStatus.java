package com.example.customer.model;

public enum CustomerStatus {
    PROSPECTIVE("Prospective"),
    CURRENT("Current"),
    NONACTIVE("Non-active");

    private String friendlyName;

    CustomerStatus(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
