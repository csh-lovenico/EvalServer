package live.bokurano.evalserver.service.impl;

import live.bokurano.evalserver.transfer.OutputCourse;
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
		GlobalProps.put("currentYear", 2019);
		GlobalProps.put("currentSemester", 1);
		int currentYear = (Integer) GlobalProps.get("currentYear");
		int currentSemester = (Integer) GlobalProps.get("currentSemester");
		ServerResult result = new ServerResult();
		try {
			List<Course> courses = studentMapper.findEnrolledCourse(studentId, currentYear, currentSemester);
			if (singleEvaluationRepository.findAllByCourseIdInAndCurrentStudentId(courses.stream().map(Course::getCourseId).collect(Collectors.toList()), studentId).isEmpty()) {
				List<OutputCourse> outputCourseList = courses.stream().map(it -> new OutputCourse(it.getCourseId(), it.getCourseName(), it.getCourseTeacher().getTeacherName(), it.getCourseTeacher().getTeacherId(), currentYear, it.getCourseSemester())).collect(Collectors.toList());
				result.setSuccess(true);
				result.setResult(outputCourseList);
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
			List<SingleEvaluation> evaluationList = singleEvaluationRepository.findAllByCurrentStudentId(studentId);
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
	public ServerResult saveEvaluation(List<SingleEvaluation> evaluations) {
		ServerResult result = new ServerResult();
		try {
			singleEvaluationRepository.saveAll(evaluations);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
