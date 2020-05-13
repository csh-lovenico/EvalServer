package live.bokurano.evalserver.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SingleEvaluation {
	@Id
	private String id;
	private String currentStudentId;
	private String courseId;
	private String courseName;
	private String courseTeacher;
	private String courseTeacherId;
	private int courseSemester;
	private int courseYear;
	private int rate;
	private String comment;
}
