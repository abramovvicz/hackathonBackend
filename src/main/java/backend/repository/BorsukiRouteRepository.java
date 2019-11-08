package backend.repository;

import backend.model.BorsukiRoute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorsukiRouteRepository extends MongoRepository<BorsukiRoute, String> {

    List<BorsukiRoute> findAllBorsukiRouteByDestinationPlace(String destinationPlace);
}
