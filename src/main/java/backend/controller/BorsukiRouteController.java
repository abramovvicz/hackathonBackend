package backend.controller;

import backend.model.BorsukiRoute;
import backend.repository.BorsukiRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorsukiRouteController {
    @Autowired
    private BorsukiRouteRepository locationRepository;

    @RequestMapping("/save")
    public ResponseEntity saveBorsukiRoute(@RequestBody BorsukiRoute borsukiRoute) {
        locationRepository.save(borsukiRoute);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
