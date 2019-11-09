package backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
public class BorsukiRoute {
    @Id
    private String id;
    private List<Point> route;
    private String startingName;
    private String destinationName;
    private String driverName;
    private String phoneNumber;
    private String dateTime;
    private Double distance;
}
