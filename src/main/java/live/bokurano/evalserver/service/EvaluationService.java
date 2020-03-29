package live.bokurano.evalserver.service;

import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.model.mongo.Evaluation;
import live.bokurano.evalserver.model.mongo.SingleEvaluation;

public interface EvaluationService {
	ServerResult getCourseEvaluations(String courseId);

	ServerResult getSpecificEvaluation(String courseId, int year);

	ServerResult saveSingleEvaluation(SingleEvaluation singleEvaluation);

	ServerResult saveEvaluation(Evaluation evaluation);

	ServerResult getCourseInfo(String courseId);
}
