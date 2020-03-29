package live.bokurano.evalserver.common;

import lombok.Data;

@Data
public class JsonResult {
	private int status;
	private Object result;
	private String message;
}
