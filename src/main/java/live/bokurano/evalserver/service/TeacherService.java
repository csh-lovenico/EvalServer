package live.bokurano.evalserver.service;

import live.bokurano.evalserver.common.ServerResult;

public interface TeacherService {
	ServerResult getTeacherCourse(String teacherId);

	ServerResult getTeacherCourseId(String teacherId);

	ServerResult getEvaluationOfMyCourse(String teacherId);
}
