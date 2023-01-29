package io.github.mimoguz.layeredfonticon.demo;

public enum Symbol {
    BORDER("\ue900"),
    BOTTOM("\ue902"),
    TOP("\ue901");

    private final String text;

    public String text() {
        return text;
    }

    Symbol(String text) {
        this.text = text;
    }
}
