package live.bokurano.evalserver.repository;

import live.bokurano.evalserver.model.mongo.RateDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateDetailsRepository extends MongoRepository<RateDetails, String> {
	List<RateDetails> findByCourseIdAndYear(String courseId, int year);
}
