package live.bokurano.evalserver.service.impl;

import live.bokurano.evalserver.mapper.TeacherMapper;
import live.bokurano.evalserver.model.mysql.Course;
import live.bokurano.evalserver.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public List<Course> getTeacherCourse(String teacherId) {
		return teacherMapper.findTeacherCourse(teacherId);
	}

	@Override
	public List<String> getTeacherCourseId(String teacherId) {
		return teacherMapper.findTeacherCourse(teacherId).stream().map(Course::getCourseId).collect(Collectors.toList());
	}
}
