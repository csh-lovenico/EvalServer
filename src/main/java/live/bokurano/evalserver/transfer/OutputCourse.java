package live.bokurano.evalserver.transfer;

import lombok.Data;

import java.io.Serializable;

@Data
public class OutputCourse implements Serializable {
	private final String courseId;
	private final String courseName;
	private final String courseTeacher;
	private final String courseTeacherId;
	private final int courseYear;
	private final int courseSemester;
}
