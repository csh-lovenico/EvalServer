package live.bokurano.evalserver.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult implements Serializable {
	private int status;
	private Object result;
	private String message;
}
