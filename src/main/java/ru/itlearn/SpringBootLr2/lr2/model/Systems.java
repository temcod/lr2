package ru.itlearn.SpringBootLr2.lr2.model;

public enum Systems {

    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System");

    private final String description;

    Systems(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
