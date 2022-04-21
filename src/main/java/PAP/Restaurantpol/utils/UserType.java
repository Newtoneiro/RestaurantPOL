package PAP.Restaurantpol.utils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
    ADMIN ("Y"),
    USER ("N");
    
    public final String dbValue;

    private UserType(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue(){return this.dbValue;}

    @Override
    public String toString() {
        return dbValue;
    }

    @JsonCreator
    public static UserType create(String dbValue) {
        if (dbValue == null) {
            throw new IllegalArgumentException();
        }
        for (UserType r : UserType.values()) {
            if (r.getDbValue().equals(dbValue)) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }
};
