package live.bokurano.evalserver.mapper;

import live.bokurano.evalserver.model.mysql.Course;
import live.bokurano.evalserver.model.mysql.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

	@Select("SELECT * FROM student WHERE student_id=#{studentId}")
	@Results({
			@Result(property = "studentId", column = "student_id"),
			@Result(property = "studentPassword", column = "student_password"),
			@Result(property = "studentName", column = "student_name")
	})
	Student loadStudent(@Param("studentId") String studentId);

	@Select("SELECT * FROM enrollment LEFT JOIN course ON enrollment.enroll_course = course.course_id LEFT JOIN teacher t on course.course_teacher = t.teacher_id WHERE enroll_student = #{studentId} AND enroll_year = #{enrollYear} AND course_semester = #{semester}")
	@Results({
			@Result(property = "courseId", column = "course_id"),
			@Result(property = "courseName", column = "course_name"),
			@Result(property = "courseSemester", column = "course_semester"),
			@Result(property = "courseTeacher", column = "course_teacher", one = @One(select = "live.bokurano.evalserver.mapper.TeacherMapper.findTeacherByIdNoPassword"))
	})
	List<Course> findEnrolledCourse(@Param("studentId") String studentId, @Param("enrollYear") int enrollYear, @Param("semester") int semester);
}
