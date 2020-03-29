package live.bokurano.evalserver.service.impl;

import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.mapper.TeacherMapper;
import live.bokurano.evalserver.model.mongo.Evaluation;
import live.bokurano.evalserver.model.mysql.Course;
import live.bokurano.evalserver.repository.EvaluationRepository;
import live.bokurano.evalserver.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherMapper teacherMapper;

	@Autowired
	private EvaluationRepository evaluationRepository;

	@Override
	public ServerResult getTeacherCourse(String teacherId) {
		ServerResult result = new ServerResult();
		try {
			List<Course> courses = teacherMapper.findTeacherCourse(teacherId);
			result.setSuccess(true);
			result.setResult(courses);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServerResult getTeacherCourseId(String teacherId) {
		ServerResult result = new ServerResult();
		try {
			List<String> courseIds = teacherMapper.findTeacherCourse(teacherId).stream().map(Course::getCourseId).collect(Collectors.toList());
			result.setResult(courseIds);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServerResult getEvaluationOfMyCourse(String teacherId) {
		ServerResult result = new ServerResult();
		try {
			List<String> courseIds = teacherMapper.findTeacherCourse(teacherId).stream().map(Course::getCourseId).collect(Collectors.toList());
			List<Evaluation> evaluations = evaluationRepository.findAllByCourseId(courseIds);
			result.setSuccess(true);
			result.setResult(evaluations);
		} catch (Exception e) {
			result.setSuccess(false);
			log.error(e.getMessage(), e);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
