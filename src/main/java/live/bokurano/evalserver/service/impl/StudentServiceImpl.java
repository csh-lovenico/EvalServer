package live.bokurano.evalserver.service.impl;

import live.bokurano.evalserver.mapper.StudentMapper;
import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import live.bokurano.evalserver.model.mysql.Course;
import live.bokurano.evalserver.repository.SingleEvaluationRepository;
import live.bokurano.evalserver.service.StudentService;
import live.bokurano.evalserver.util.GlobalProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private SingleEvaluationRepository singleEvaluationRepository;

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Course> getUnevaluatedCourse(String studentId) {
		int currentYear = (Integer) GlobalProps.get("currentYear");
		int currentSemester = (Integer) GlobalProps.get("currentSemester");
		List<Course> courses = studentMapper.findEnrolledCourse(studentId, currentYear, currentSemester);
		if (singleEvaluationRepository.findAllByCourseIdAndStudentId(courses.stream().map(Course::getCourseId).collect(Collectors.toList()),studentId).isEmpty()){
			return new ArrayList<>();
		} else {
			return courses;
		}
	}

	@Override
	public List<SingleEvaluation> getEvaluationHistory(String studentId) {
		return singleEvaluationRepository.findAllByStudentId(studentId);
	}
}
