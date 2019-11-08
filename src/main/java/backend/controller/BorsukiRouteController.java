package backend.controller;

import backend.model.BorsukiRoute;
import backend.repository.BorsukiRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorsukiRouteController {
    @Autowired
    private BorsukiRouteRepository locationRepository;

    @PostMapping("/save")
    public ResponseEntity saveBorsukiRoute(@RequestBody BorsukiRoute borsukiRoute) {
        locationRepository.save(borsukiRoute);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/all", produces = "application/json")
    public ResponseEntity<List<BorsukiRoute>> getAllBorsukiRoute() {
        List<BorsukiRoute> allBorsukiRoutes = locationRepository.findAll();
        return new ResponseEntity<>(allBorsukiRoutes, HttpStatus.OK);
    }
}
