package io.github.mimoguz.layeredfonticon.demo;

public enum Symbol {
    BORDER("\ue900"),
    BOTTOM("\ue902"),
    TOP("\ue901");

    private final String value;

    public String value() {
        return value;
    }

    Symbol(String value) {
        this.value = value;
    }
}
