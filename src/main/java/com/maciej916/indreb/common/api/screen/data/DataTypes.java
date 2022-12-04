package com.maciej916.indreb.common.api.screen.data;

public enum DataTypes {
    NONE(1),
    PROGRESS_FLOAT(2),
    PROGRESS_INT(3),
    BOOL(4),
    INT(5);

    private final int id;

    DataTypes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DataTypes getFromId(int id) {
        DataTypes[] types = DataTypes.values();
        for (DataTypes type : types) {
            if (type.getId() == id) return type;
        }
        return NONE;
    }
}
