package backend.controller;

import backend.model.BorsukiRoute;
import backend.service.BorsukiRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorsukiRouteController {
    @Autowired
    private BorsukiRouteService borsukiRouteService;

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity insertBorsukiRoute(@RequestBody BorsukiRoute borsukiRoute) {
        borsukiRouteService.insertBorsukiRoute(borsukiRoute);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/all", produces = "application/json")
    public ResponseEntity<List<BorsukiRoute>> findAllBorsukiRoutes() {
        List<BorsukiRoute> allBorsukiRoutes = borsukiRouteService.findAllBorsukiRoute();
        return new ResponseEntity<>(allBorsukiRoutes, HttpStatus.OK);
    }

    @GetMapping(value = "/find/byDestination", produces = "application/json")
    public ResponseEntity<List<BorsukiRoute>> findAllBorsukiRouteByDestinationName(@RequestParam("destination") String destinationName) {
        List<BorsukiRoute> borsukiRoutesForDestinationName = borsukiRouteService.findAllBorsukiRouteByDestinationName(destinationName);
        return new ResponseEntity<>(borsukiRoutesForDestinationName, HttpStatus.OK);
    }
}
