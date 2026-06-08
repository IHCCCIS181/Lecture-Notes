package org.example.readonlystudentapi.model;

public enum Gender {
    M( "Male"),
    F("Female"),
    N( "Non-binary"),
    O( "Other");

    private final String fullName;

    Gender(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
