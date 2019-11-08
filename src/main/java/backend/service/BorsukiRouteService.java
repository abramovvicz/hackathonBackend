package backend.service;

import backend.model.BorsukiRoute;
import backend.repository.BorsukiRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<BorsukiRoute> findAllBorsukiRouteByDestinationPlace(String destinationPlace) {
        return borsukiRouteRepository.findAllBorsukiRouteByDestinationPlace(destinationPlace);
    }
}
