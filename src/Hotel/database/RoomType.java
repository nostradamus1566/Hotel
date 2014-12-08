package Hotel.database;

public enum RoomType {
    // Numeric types, for the database
    PENTHOUSE(4), FAMILY(3), DOUBLE(2), TWIN(1), SINGLE(0), UNKNOWN(-1);

    private Integer value;

    RoomType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RoomType getFromValue(Integer value) {
        switch(value) {
            case(4):
                return PENTHOUSE;
            case (3):
                return FAMILY;
            case(2):
                return DOUBLE;
            case(1):
                return TWIN;
            case(0):
                return SINGLE;
            default:
                return UNKNOWN;
        }
    }
}
