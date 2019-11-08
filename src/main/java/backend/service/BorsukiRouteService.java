package backend.service;

import backend.model.BorsukiRoute;
import backend.model.Point;
import backend.repository.BorsukiRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorsukiRouteService {
    @Autowired
    private BorsukiRouteRepository borsukiRouteRepository;

    public ResponseEntity insertBorsukiRoute(BorsukiRoute borsukiRoute) {
        borsukiRouteRepository.insert(borsukiRoute);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public List<BorsukiRoute> findAllBorsukiRoute() {
        return borsukiRouteRepository.findAll();
    }

    public List<BorsukiRoute> findAllBorsukiRouteByDestinationName(String destinationName) {
        return borsukiRouteRepository.findAllBorsukiRouteByDestinationName(destinationName);
    }

    public List<BorsukiRoute> findAllCompliantRoutesForPassenger(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        List<BorsukiRoute> allDriverRoutes = findAllBorsukiRoute();
        List<BorsukiRoute> allCompliantRoutes = allDriverRoutes.stream()
                .filter(driverRoute -> isDistanceBetweenPointsCompliant(getFirstPointOfDriversRoute(driverRoute), createPoint(startLatitude, startLongitude), driverRoute.getDistance()))
                .filter(driverRoute -> isDistanceBetweenPointsCompliant(getLastPointOfDriversRoute(driverRoute), createPoint(endLatitude, endLongitude), driverRoute.getDistance()))
                .collect(Collectors.toList());
        return allCompliantRoutes;
    }

    private boolean isDistanceBetweenPointsCompliant(Point driverPoint, Point passengerPoint, double acceptableDistance) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(passengerPoint.getLatitude()-driverPoint.getLatitude());
        double dLng = Math.toRadians(passengerPoint.getLongitude()-driverPoint.getLongitude());
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(driverPoint.getLatitude())) * Math.cos(Math.toRadians(passengerPoint.getLatitude())) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return (earthRadius * c) <= acceptableDistance;
    }

    private Point getLastPointOfDriversRoute(BorsukiRoute driverRoute) {
        return driverRoute.getRoute().get(driverRoute.getRoute().size() - 1);
    }

    private Point getFirstPointOfDriversRoute(BorsukiRoute driverRoute) {
        return driverRoute.getRoute().get(0);
    }

    private Point createPoint(double startLatitude, double startLongitude) {
        return new Point(startLatitude, startLongitude);
    }
}
