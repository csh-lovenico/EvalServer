package live.bokurano.evalserver.service;

import live.bokurano.evalserver.model.mysql.Course;

import java.util.List;

public interface TeacherService {
	List<Course> getTeacherCourse(String teacherId);
	List<String> getTeacherCourseId(String teacherId);
}
