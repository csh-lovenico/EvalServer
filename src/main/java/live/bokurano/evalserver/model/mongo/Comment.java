package live.bokurano.evalserver.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Comment {
	@Id
	private String id;
	private String studentId;
	private String courseId;
	private int year;
	private String body;
}
