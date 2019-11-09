package backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
public class PassengerRequest {
    @Id
    private String id;
    private String driverId;
    private String driverPhoneNumber;
    private Point start;
    private Point destination;
    private boolean isAccepted;

    public PassengerRequest(String driverId, Point start, Point destination) {
        this.driverId = driverId;
        this.start = start;
        this.destination = destination;
        this.isAccepted = false;
        this.driverPhoneNumber = "";
    }
}
