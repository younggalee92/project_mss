package com.younggal.project.enumType;

public enum FileType implements IEnumtype {
    IMAGE("IMAGE"),
    VIDEO("VIDEO");
    private final String value;

    FileType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
