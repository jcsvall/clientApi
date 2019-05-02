package sv.com.jcsvall.clientApi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private String user;
	private String password;
	private String token;
	public UserDto() {		
	}
	
}
