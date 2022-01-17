package com.egorbahar.enums;

import java.util.HashMap;
import java.util.Map;

public enum Position {
    JUNIOR_JAVA_DEVELOPER("Junior Java Developer"),
    MIDDLE_JAVA_DEVELOPER("Middle Java Developer"),
    SENIOR_JAVA_DEVELOPER("Senior Java Developer"),
    JUNIOR_NET_DEVELOPER("Junior C# Developer"),
    MIDDLE_NET_DEVELOPER("Middle C# Developer"),
    SENIOR_NET_DEVELOPER("Senior C# Developer");
    private final String position;
    Position(String position)
    {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public Map<String, String> getPositionList() {
        Map <String, String> map = new HashMap<>();
        for (Position position : Position.values()) {
            map.put(position.getPosition(), position.name());
        }
        return map;
    }
}
