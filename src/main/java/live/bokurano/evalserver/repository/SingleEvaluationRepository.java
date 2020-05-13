package live.bokurano.evalserver.repository;

import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleEvaluationRepository extends MongoRepository<SingleEvaluation, String> {
	List<SingleEvaluation> findAllByCourseYearAndCourseSemester(int year,int semester);

	List<SingleEvaluation> findAllByCurrentStudentId(String studentId);

	List<SingleEvaluation> findAllByCourseIdInAndCurrentStudentId(List<String> courseId, String studentId);
}
