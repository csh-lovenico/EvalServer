package live.bokurano.evalserver.repository;

import live.bokurano.evalserver.model.mongo.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation, String> {
	List<Evaluation> findAllByCourseId(String courseId);

	List<Evaluation> findAllByCourseId(List<String> courseIds);

	Optional<Evaluation> findByCourseIdAndCourseYear(String courseId, int year);

	List<Evaluation> findAllByCourseTeacherId(String teacherId);
}
