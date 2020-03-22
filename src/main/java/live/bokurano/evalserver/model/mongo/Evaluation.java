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
	private String studentId;
	int year;
	private double total;
	private RateDetails rateDetails;
	private int studentNum;
	private int likes;
	private List<Comment> comments;
}
