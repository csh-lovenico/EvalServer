package live.bokurano.evalserver.model;

import lombok.Data;

@Data
public class Course {
	private String courseId;
	private String courseName;
	private Teacher courseTeacher;
}
