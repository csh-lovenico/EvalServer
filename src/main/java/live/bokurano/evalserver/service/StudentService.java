package live.bokurano.evalserver.service;

import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import live.bokurano.evalserver.model.mysql.Course;

import java.util.List;

public interface StudentService {
	ServerResult getUnevaluatedCourse(String studentId);

	ServerResult getEvaluationHistory(String studentId);

	ServerResult saveEvaluation(SingleEvaluation evaluation);
}
