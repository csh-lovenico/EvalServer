package live.bokurano.evalserver.service.impl;

import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.mapper.StudentMapper;
import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import live.bokurano.evalserver.model.mysql.Course;
import live.bokurano.evalserver.repository.SingleEvaluationRepository;
import live.bokurano.evalserver.service.StudentService;
import live.bokurano.evalserver.util.GlobalProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

	@Autowired
	private SingleEvaluationRepository singleEvaluationRepository;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public ServerResult getUnevaluatedCourse(String studentId) {

		int currentYear = (Integer) GlobalProps.get("currentYear");
		int currentSemester = (Integer) GlobalProps.get("currentSemester");
		ServerResult result = new ServerResult();
		try {
			List<Course> courses = studentMapper.findEnrolledCourse(studentId, currentYear, currentSemester);
			if (singleEvaluationRepository.findAllByCourseIdAndStudentId(courses.stream().map(Course::getCourseId).collect(Collectors.toList()), studentId).isEmpty()) {
				result.setSuccess(true);
				result.setResult(courses);
			} else {
				result.setSuccess(true);
				result.setResult(new ArrayList<>());
			}
			return result;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			return result;
		}
	}

	@Override
	public ServerResult getEvaluationHistory(String studentId) {
		ServerResult result = new ServerResult();
		try {
			List<SingleEvaluation> evaluationList = singleEvaluationRepository.findAllByStudentId(studentId);
			result.setResult(evaluationList);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServerResult saveEvaluation(SingleEvaluation evaluation) {
		ServerResult result = new ServerResult();
		try {
			singleEvaluationRepository.save(evaluation);
			result.setSuccess(true);
		} catch (Exception e){
			result.setSuccess(false);
			log.error(e.getMessage(),e);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
