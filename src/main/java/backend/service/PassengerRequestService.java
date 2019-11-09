package backend.service;

import backend.model.PassengerRequest;
import backend.repository.PassengerRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerRequestService {
    @Autowired
    private PassengerRequestRepository passengerRequestRepository;

    public PassengerRequest findPassengerRequestByDriverId(String driverId) {
        return passengerRequestRepository.findPassengerRequestByDriverId(driverId);
    }

    public void insertPassengerRequest(PassengerRequest passengerRequest) {
        passengerRequestRepository.insert(passengerRequest);
    }

    public void updatePassengerRequestWithDriverAcceptance(String driverId, boolean isAccepted) {
        PassengerRequest passengerRequest = findPassengerRequestByDriverId(driverId);
        passengerRequest.setAccepted(isAccepted);
        passengerRequestRepository.save(passengerRequest);
    }
}
