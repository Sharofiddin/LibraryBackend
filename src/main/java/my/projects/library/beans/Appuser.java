package my.projects.library.beans;

import lombok.Data;

@Data
public class Appuser {
	private Long id;
	private String login;
	private String password;
	private String email;
	private Boolean enabled;
}
