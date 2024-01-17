package iti.jets.app.models.enums;

public enum ModeEnum {
    AVAILABLE("Available"),
    BUSY("Busy"),

    AWAY("Away");

    private String mode;

    ModeEnum(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
