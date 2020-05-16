package my.projects.library.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class Appuser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String login;
	private String password;
	private String email;
	private Boolean enabled;
}
