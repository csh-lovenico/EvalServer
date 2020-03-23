package live.bokurano.evalserver.service;

import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import live.bokurano.evalserver.model.mysql.Course;

import java.util.List;

public interface StudentService {
	List<Course> getUnevaluatedCourse(String studentId);

	List<SingleEvaluation> getEvaluationHistory(String studentId);
}
