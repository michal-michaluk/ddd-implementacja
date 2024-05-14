package devices.configuration.protocols.iot16;

import devices.configuration.intervals.DeviceInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BootNotificationRequestTest {

    @Test
    void toDeviceInfo() {
        BootNotificationRequest message = Ocpp16MessagesFixture.bootNotification();
        DeviceInfo info = message.toDeviceInfo("EVB-P4562137");

        Assertions.assertThat(info)
                .isEqualTo(Ocpp16MessagesFixture.deviceInfoMatchingExample());
    }
}
