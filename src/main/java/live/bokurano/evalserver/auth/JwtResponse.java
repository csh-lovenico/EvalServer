package live.bokurano.evalserver.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
	private final String jwtToken;
	private final String userRole;
	private final String userId;
}
