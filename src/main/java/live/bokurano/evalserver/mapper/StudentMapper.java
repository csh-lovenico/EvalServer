package live.bokurano.evalserver.mapper;

import live.bokurano.evalserver.model.Student;
import org.apache.ibatis.annotations.*;

public interface StudentMapper {

	@Select("SELECT * FROM student WHERE student_id=#{studentId} AND student_password=#{studentPassword}")
	@Results({
			@Result(property = "studentId", column = "student_id"),
			@Result(property = "studentName", column = "student_name")
	})
	Student studentLogin(@Param("studentId") String studentId, @Param("studentPassword") String studentPassword);

	@Select("SELECT * FROM student WHERE student_id=#{studentId}")
	@Results({
			@Result(property = "studentId", column = "student_id"),
			@Result(property = "studentPassword", column = "student_password"),
			@Result(property = "studentName", column = "student_name")
	})
	Student loadStudent(@Param("studentId") String studentId);
}
