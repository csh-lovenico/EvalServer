package live.bokurano.evalserver.model.mysql;

import lombok.Data;

@Data
public class Course {
	private String courseId;
	private String courseName;
	private int courseSemester;
	private Teacher courseTeacher;
}
