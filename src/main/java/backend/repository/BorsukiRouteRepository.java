package backend.repository;

import backend.model.BorsukiRoute;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorsukiRouteRepository extends MongoRepository<BorsukiRoute, String> {

}
