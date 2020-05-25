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
	private final String courseId;
	private final String courseName;
	private final String courseTeacher;
	private final String courseTeacherId;
	private final int courseSemester;
	private final int courseYear;
	private List<Double> average;
	private int studentNum;
	private List<String> comments;
}
