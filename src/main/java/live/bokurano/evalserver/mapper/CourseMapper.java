package live.bokurano.evalserver.mapper;

import live.bokurano.evalserver.model.mysql.Course;
import org.apache.ibatis.annotations.*;

public interface CourseMapper {

	@Select("SELECT * FROM course LEFT JOIN teacher ON course_teacher=teacher_id WHERE course_id=#{courseId}")
	@Results({
			@Result(property = "courseId",column = "course_id"),
			@Result(property = "courseName",column = "course_name"),
			@Result(property = "courseSemester",column = "course_semester"),
			@Result(property = "courseTeacher", column = "course_teacher", one = @One(select = "live.bokurano.evalserver.mapper.TeacherMapper.findTeacherByIdNoPassword"))
	})
	Course findCourseById(@Param("courseId") String courseId);
}
