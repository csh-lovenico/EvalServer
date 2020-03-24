package live.bokurano.evalserver.service.impl;

import live.bokurano.evalserver.model.mongo.Evaluation;
import live.bokurano.evalserver.repository.EvaluationRepository;
import live.bokurano.evalserver.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
	private EvaluationRepository evaluationRepository;

	@Override
	public List<Evaluation> getCourseEvaluations(String courseId) {
		return evaluationRepository.findAllByCourseId(courseId);
	}

	@Override
	public Evaluation getSpecificEvaluation(String courseId, int year) {
		return evaluationRepository.findByCourseIdAndYear(courseId, year).orElse(null);
	}
}
