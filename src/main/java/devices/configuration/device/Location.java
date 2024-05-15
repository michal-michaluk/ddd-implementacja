package devices.configuration.device;

import java.math.BigDecimal;
import java.util.Objects;

record Location(
        String street, String houseNumber,
        String city, String postalCode,
        String state, String country,
        Coordinates coordinates) {

    Location {
        Objects.requireNonNull(coordinates);
    }

    record Coordinates(BigDecimal longitude,
                       BigDecimal latitude) {
        Coordinates {
            Objects.requireNonNull(longitude);
            Objects.requireNonNull(latitude);
        }
    }
}
