package devices.configuration.device;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

record Location(
        @NotBlank String street, @NotBlank String houseNumber,
        @NotBlank String city, @NotBlank String postalCode,
        String state, @NotBlank String country,
        @Valid Coordinates coordinates) {

    record Coordinates(@NotNull BigDecimal longitude,
                       @NotNull BigDecimal latitude) {
    }
}
