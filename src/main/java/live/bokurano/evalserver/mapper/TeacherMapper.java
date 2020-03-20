package live.bokurano.evalserver.mapper;

import live.bokurano.evalserver.model.Teacher;
import live.bokurano.evalserver.model.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {

	@Select("SELECT * FROM teacher WHERE teacher_id=#{teacherId} AND teacher_password=#{teacherPassword}")
	@Results({
			@Result(property = "teacherId", column = "teacher_id"),
			@Result(property = "teacherName", column = "teacher_name")
	})
	Teacher teacherLogin(@Param("teacherId") String teacherId, @Param("teacherPassword") String teacherPassword);

	@Select("SELECT * FROM teacher WHERE teacher_id=#{teacherId}")
	@Results({
			@Result(property = "teacherId", column = "teacher_id"),
			@Result(property = "teacherPassword", column = "teacher_password"),
			@Result(property = "teacherName", column = "teacher_name")
	})
	Teacher loadTeacher(@Param("teacherId") String teacherId);
}
