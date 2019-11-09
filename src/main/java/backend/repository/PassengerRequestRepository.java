package backend.repository;

import backend.model.PassengerRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRequestRepository extends MongoRepository<PassengerRequest, String> {
    PassengerRequest findPassengerRequestByDriverId(String driverId);
}
