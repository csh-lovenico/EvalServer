package live.bokurano.evalserver.mapper;

import live.bokurano.evalserver.model.mysql.Course;
import live.bokurano.evalserver.model.mysql.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {

	@Select("SELECT * FROM teacher WHERE teacher_id=#{teacherId}")
	@Results({
			@Result(property = "teacherId", column = "teacher_id"),
			@Result(property = "teacherPassword", column = "teacher_password"),
			@Result(property = "teacherName", column = "teacher_name")
	})
	Teacher loadTeacher(@Param("teacherId") String teacherId);

	@Select("SELECT * FROM teacher WHERE teacher_id=#{teacherId}")
	@Results({
			@Result(property = "teacherId", column = "teacher_id"),
			@Result(property = "teacherName", column = "teacher_name")
	})
	Teacher findTeacherByIdNoPassword(@Param("teacherId") String teacherId);

	@Select("SELECT * FROM course LEFT JOIN teacher ON course_teacher=teacher_id WHERE course_teacher=#{teacherId}")
	@Results({
			@Result(property = "courseId", column = "course_id"),
			@Result(property = "courseName", column = "course_name"),
			@Result(property = "courseSemester", column = "course_semester"),
	})
	List<Course> findTeacherCourse(@Param("teacherId") String teacherId);
}
