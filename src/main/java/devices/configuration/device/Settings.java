package devices.configuration.device;

import lombok.Builder;

@Builder(toBuilder = true)
record Settings(
        Boolean autoStart,
        Boolean remoteControl,
        Boolean billing,
        Boolean reimbursement,
        Boolean showOnMap,
        Boolean publicAccess) {

    static Settings defaultSettings() {
        return builder()
                .autoStart(false)
                .remoteControl(false)
                .billing(false)
                .reimbursement(false)
                .showOnMap(false)
                .publicAccess(false)
                .build();
    }
}
