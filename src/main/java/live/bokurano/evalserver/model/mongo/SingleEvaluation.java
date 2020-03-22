package live.bokurano.evalserver.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SingleEvaluation {
	@Id
	private String id;
	private String studentId;
	private String courseId;
	private int year;
	private int rate;
	private boolean like;
	private String comment;
}
