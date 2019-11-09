package backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PassengerRequest {
    @Id
    private String id;
    private String driverId;
    private String driverPhoneNumber;
    private Point start;
    private Point destination;
    private Point driverStart;
    private Point driverDestination;
    private boolean isAccepted;

    public PassengerRequest(String driverId, Point start, Point destination) {
        this.driverId = driverId;
        this.start = start;
        this.destination = destination;
        this.driverDestination = null;
        this.isAccepted = false;
        this.driverPhoneNumber = "";
    }
}
