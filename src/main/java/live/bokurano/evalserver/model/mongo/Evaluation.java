package live.bokurano.evalserver.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Evaluation {
	@Id
	private String id;
	private String courseId;
	private String courseName;
	private String courseTeacher;
	private String courseTeacherId;
	private int courseSemester;
	private int courseYear;
	private double average;
	private int studentNum;
	private List<String> comments;
}
