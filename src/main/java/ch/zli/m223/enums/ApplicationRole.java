package ch.zli.m223.enums;

public enum ApplicationRole {
    ADMINISTRATOR("ADMINISTRATOR"),
    USER("USER"),
    DEVELOPER("DEVELOPER");

    private final String label;

    private ApplicationRole(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }

    public static ApplicationRole fromString(String label) {
        for (ApplicationRole role : values()) {
            if (role.label.equals(label.toUpperCase())) {
                return role;
            }
        }
        return null;
    }
}
