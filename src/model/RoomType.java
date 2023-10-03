package model;

public enum RoomType {
    SINGLE("1"),
    DOUBLE("2"),
    ;

    //baeldung.com/java-enum-values
    public final String label;

    private RoomType(String label) {
        this.label = label;
    }

    public static RoomType valueOfLabel(String label) {
        for (RoomType roomType : values()) {
            if (roomType.label.equals(label)) {
                return roomType;
            }
        }
        return null;
    }
}
