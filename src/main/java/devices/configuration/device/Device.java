package devices.configuration.device;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
class Device {
    final String deviceId;
    private Ownership ownership;
    private Location location;
    private OpeningHours openingHours;
    private Settings settings;

    static Device create(String deviceId) {
        return new Device(
                deviceId,
                Ownership.unowned(),
                null,
                OpeningHours.alwaysOpen(),
                Settings.defaultSettings()
        );
    }

    void assignTo(Ownership ownership) {
        ownership = Objects.requireNonNullElse(ownership, Ownership.unowned());
        if (ownership.isUnowned()) {
            resetConfiguration();
        }
        ensureOperatorIsNotSwitched(ownership);
        this.ownership = ownership;
    }

    void resetConfiguration() {
        updateLocation(null);
        updateOpeningHours(OpeningHours.alwaysOpen());
        updateSettings(Settings.defaultSettings());
    }

    void updateLocation(Location location) {
        this.location = location;
    }

    void updateOpeningHours(OpeningHours openingHours) {
        Objects.requireNonNull(openingHours);
        this.openingHours = openingHours;
    }

    void updateSettings(Settings settings) {
        Objects.requireNonNull(settings);
        this.settings = this.settings.merge(settings);
    }

    private void ensureOperatorIsNotSwitched(Ownership ownership) {
        if (!this.ownership.isUnowned() && !Objects.equals(this.ownership.operator(), ownership.operator())) {
            throw new IllegalArgumentException();
        }
    }

    private Violations checkViolations() {
        return Violations.builder()
                .operatorNotAssigned(ownership.operator() == null)
                .providerNotAssigned(ownership.provider() == null)
                .locationMissing(location == null)
                .showOnMapButMissingLocation(settings.isShowOnMap() && location == null)
                .showOnMapButNoPublicAccess(settings.isShowOnMap() && !settings.isPublicAccess())
                .build();
    }

    DeviceSnapshot toSnapshot() {
        Violations violations = checkViolations();
        Visibility visibility = Visibility.basedOn(
                violations.isValid() && settings.isPublicAccess(),
                settings.isShowOnMap()
        );
        return new DeviceSnapshot(
                deviceId,
                ownership,
                location,
                openingHours,
                settings,
                violations,
                visibility
        );
    }
}
