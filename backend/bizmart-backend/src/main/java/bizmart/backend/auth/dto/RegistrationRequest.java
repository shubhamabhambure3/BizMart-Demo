package bizmart.backend.auth.dto;

import bizmart.backend.auth.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
	
	private String fullName;
	private String email;
	private String mobile;
	private String password;
	private Role role;
	
}
