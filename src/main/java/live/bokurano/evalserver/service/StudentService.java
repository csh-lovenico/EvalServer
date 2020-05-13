package live.bokurano.evalserver.service;

import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.model.mongo.SingleEvaluation;

import java.util.List;

public interface StudentService {
	ServerResult getUnevaluatedCourse(String studentId);

	ServerResult getEvaluationHistory(String studentId);

	ServerResult saveEvaluation(List<SingleEvaluation> evaluations);
}
