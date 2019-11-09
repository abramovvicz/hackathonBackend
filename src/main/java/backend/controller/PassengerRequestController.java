package backend.controller;

import backend.model.BorsukiRoute;
import backend.model.PassengerRequest;
import backend.model.Point;
import backend.service.BorsukiRouteService;
import backend.service.PassengerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PassengerRequestController {

    @Autowired
    private PassengerRequestService passengerRequestService;
    @Autowired
    private BorsukiRouteService borsukiRouteService;

    @PostMapping(value = "/request/insert")
    public ResponseEntity getPassengerRequest(@RequestBody PassengerRequest passengerRequest) {
        passengerRequestService.insertPassengerRequest(passengerRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/request/find", produces = "application/json")
    public ResponseEntity<PassengerRequest> findPassengerRequestByDriverId(@RequestParam("driverId") String driverId) {
        BorsukiRoute driverRoute = borsukiRouteService.findBorsukiRouteById(driverId);
        PassengerRequest passengerRequest = passengerRequestService.findPassengerRequestByDriverId(driverId);
        passengerRequest.setDriverStart(getDriverStartPoint(driverRoute));
        passengerRequest.setDriverDestination(getDriverDestinationPoint(driverRoute));
        return new ResponseEntity<>(passengerRequest, HttpStatus.OK);
    }

    private Point getDriverDestinationPoint(BorsukiRoute driverRoute) {
        return driverRoute.getRoute().get(driverRoute.getRoute().size() - 1);
    }

    private Point getDriverStartPoint(BorsukiRoute driverRoute) {
        return driverRoute.getRoute().get(0);
    }

    @PutMapping(value = "/request/update")
    public ResponseEntity checkIfDriveWasAcceptedByDriver(@RequestParam("driverId") String driverId, @RequestParam("isAccepted") boolean isAccepted) {
        passengerRequestService.updatePassengerRequestWithDriverAcceptance(driverId, isAccepted);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/request/check", produces = "application/json")
    public ResponseEntity<PassengerRequest> checkPassengerRequestByDriverId(@RequestParam("driverId") String driverId) {
        PassengerRequest passengerRequest = passengerRequestService.findPassengerRequestByDriverId(driverId);
        fillPhoneNumberIfAccepted(driverId, passengerRequest);
        return new ResponseEntity<>(passengerRequest, HttpStatus.OK);
    }

    private void fillPhoneNumberIfAccepted(@RequestParam("driverId") String driverId, PassengerRequest passengerRequest) {
        if (passengerRequest.isAccepted()) {
            BorsukiRoute borsukiRoute = borsukiRouteService.findBorsukiRouteById(driverId);
            passengerRequest.setDriverPhoneNumber(borsukiRoute.getPhoneNumber());
        }
    }
    
}
