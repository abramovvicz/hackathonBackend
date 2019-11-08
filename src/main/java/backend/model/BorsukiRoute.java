package backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BorsukiRoute {
    private List<Point> route;
    private String startingName;
    private String destinationName;
    private String driverName;
    private String phoneNumber;
    private String dateTime;
    private Double distance;
}
