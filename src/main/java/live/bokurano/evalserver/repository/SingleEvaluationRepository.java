package live.bokurano.evalserver.repository;

import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleEvaluationRepository extends MongoRepository<SingleEvaluation, String> {
	List<SingleEvaluation> findAllByYear(int year);

	List<SingleEvaluation> findAllByStudentId(String studentId);

	List<SingleEvaluation> findAllByCourseIdAndStudentId(List<String> courseId, String studentId);
}
