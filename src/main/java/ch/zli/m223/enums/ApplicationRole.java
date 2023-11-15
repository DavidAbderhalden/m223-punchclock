package ch.zli.m223.enums;

public enum ApplicationRole {
    ADMINISTRATOR("administrator"),
    USER("user"),
    DEVELOPER("developer");

    private final String label;

    private ApplicationRole(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }

    public static ApplicationRole fromString(String label) {
        for (ApplicationRole role : values()) {
            if (role.label.equals(label)) {
                return role;
            }
        }
        return null;
    }
}
