package live.bokurano.evalserver.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class RateDetails {
	@Id
	private String id;
	private String studentId;
	private String courseId;
	private int year;
	private int rate_5;
	private int rate_4;
	private int rate_3;
	private int rate_2;
	private int rate_1;
}
