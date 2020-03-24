package live.bokurano.evalserver.service;

import live.bokurano.evalserver.model.mongo.Evaluation;

import java.util.List;

public interface EvaluationService {
	List<Evaluation> getCourseEvaluations(String courseId);

	Evaluation getSpecificEvaluation(String courseId, int year);
}
