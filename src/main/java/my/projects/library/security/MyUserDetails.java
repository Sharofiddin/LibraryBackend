package my.projects.library.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import my.projects.library.beans.Appuser;
import my.projects.library.beans.Role;
import my.projects.library.db.MyBatisHelper;

public class MyUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;

	private Appuser user;
	
	public MyUserDetails(Appuser appuser) {
		this.user = appuser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<Role> roles = new MyBatisHelper().selectList("selectRolesByUserId", user.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
         
        return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

}
