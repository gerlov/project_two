package com.kth.project_dollarstore.model;

public enum Reason {
    NOT_FOUND_PRODUCTS("Did not find products I wanted"),
    POOR_CUSTOMER_SERVICE("Poor customer service experience"),
    BETTER_PRICES("Found better prices elsewhere"),
    PRIVACY_CONCERNS("Privacy concerns"),
    OTHER("Other (please specify)");

    private final String description;

    Reason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}