package backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(value = "BorsukiRoute")
public class BorsukiRoute {
    private List<Point>  route;
    private String startingPlace;
    private String destinationPlace;
    private String driverName;
    private String phoneNumber;
    private LocalDateTime dateTime;
    private Double distance;

}
