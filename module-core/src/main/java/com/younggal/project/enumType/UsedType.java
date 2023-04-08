package com.younggal.project.enumType;

public enum UsedType implements IEnumtype {
    CATEGORY_BANNER("CATEGORY_BANNER");
    private final String value;

    UsedType(String value) {
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
