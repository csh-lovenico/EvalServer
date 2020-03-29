package live.bokurano.evalserver.common;

import lombok.Data;

@Data
public class ServerResult {
	private boolean success;
	private Object result;
	private String message;
}
