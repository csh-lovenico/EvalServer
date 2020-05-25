package live.bokurano.evalserver.service.impl;

import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.mapper.CourseMapper;
import live.bokurano.evalserver.model.mongo.Evaluation;
import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import live.bokurano.evalserver.model.mysql.Course;
import live.bokurano.evalserver.repository.EvaluationRepository;
import live.bokurano.evalserver.repository.SingleEvaluationRepository;
import live.bokurano.evalserver.service.EvaluationService;
import live.bokurano.evalserver.util.GlobalProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
	private SingleEvaluationRepository singleEvaluationRepository;

	@Autowired
	private EvaluationRepository evaluationRepository;

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public ServerResult getCourseEvaluations(String courseId) {
		ServerResult result = new ServerResult();
		try {
			List<Evaluation> evaluations = evaluationRepository.findAllByCourseId(courseId);
			result.setSuccess(true);
			result.setResult(evaluations);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServerResult saveSingleEvaluation(SingleEvaluation singleEvaluation) {
		ServerResult result = new ServerResult();
		try {
			singleEvaluationRepository.save(singleEvaluation);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServerResult saveEvaluation(Evaluation evaluation) {
		ServerResult result = new ServerResult();
		try {
			evaluationRepository.save(evaluation);
			result.setSuccess(true);
			result.setResult(evaluation);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public ServerResult getSpecificEvaluation(String courseId, int year) {
		ServerResult result = new ServerResult();
		try {
			Evaluation evaluation = evaluationRepository.findByCourseIdAndCourseYear(courseId, year).orElse(null);
			result.setResult(evaluation);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServerResult getCourseInfo(String courseId) {
		ServerResult result = new ServerResult();
		try {
			Course course = courseMapper.findCourseById(courseId);
			result.setResult(course);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
		}

		return null;
	}

	@Override
	public ServerResult generateStats() {
		List<SingleEvaluation> singleEvaluationList = singleEvaluationRepository.
				findAllByCourseYearAndCourseSemester((Integer) GlobalProps.get("currentYear"),
						(Integer) GlobalProps.get("currentSemester"));
		Map<String, List<SingleEvaluation>> evaluationGroups = new ConcurrentHashMap<>();
		List<Evaluation> evaluationList = new CopyOnWriteArrayList<>();
		singleEvaluationList.parallelStream().forEach(it -> {
			if (evaluationGroups.get(it.getCourseId()) == null) {
				List<SingleEvaluation> evaluations = new CopyOnWriteArrayList<>();
				evaluations.add(it);
				evaluationGroups.put(it.getCourseId(), evaluations);
			} else {
				evaluationGroups.get(it.getCourseId()).add(it);
			}
		});
		log.info(evaluationGroups.toString());
		evaluationGroups.forEach((key, value) -> {
			AtomicInteger totalNum = new AtomicInteger(0);
			List<String> comments = new CopyOnWriteArrayList<>();
			SingleEvaluation firstElement = value.get(0);
			Integer[][] rateArray = new Integer[value.size()][firstElement.getRate().size()];
			value.stream().forEach(it -> {
				comments.add(it.getComment());
				rateArray[totalNum.get()] = it.getRate().toArray(new Integer[0]);
				totalNum.getAndIncrement();
			});
			Integer[][] transposedArray = new Integer[firstElement.getRate().size()][value.size()];
			for (int i = 0; i < value.size(); i++) {
				for (int j = 0; j < firstElement.getRate().size(); j++) {
					transposedArray[j][i] = rateArray[i][j];
				}
			}
			Double[] resultArray = new Double[firstElement.getRate().size()];
			for (int i = 0; i < firstElement.getRate().size(); i++) {
				double average = List.of(transposedArray[i]).stream().mapToDouble(it -> it).average().getAsDouble();
				resultArray[i] = average;
			}
			log.info(Arrays.deepToString(transposedArray));
			log.info(Arrays.toString(resultArray));

			Evaluation evaluation = new Evaluation(firstElement.getCourseId(), firstElement.getCourseName(),
					firstElement.getCourseTeacher(), firstElement.getCourseTeacherId(), firstElement.getCourseSemester(),
					firstElement.getCourseYear());
			evaluation.setAverage(List.of(resultArray));
			evaluation.setComments(comments);
			evaluation.setStudentNum(totalNum.get());
			evaluationList.add(evaluation);
		});
		log.info(evaluationList.toString());
		evaluationRepository.saveAll(evaluationList);
		ServerResult result = new ServerResult();
		result.setSuccess(true);
		return result;
	}
}
