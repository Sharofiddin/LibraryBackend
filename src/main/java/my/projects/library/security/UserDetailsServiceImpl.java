package my.projects.library.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import my.projects.library.beans.Appuser;
import my.projects.library.db.MyBatisHelper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username){
		Appuser appuser = new MyBatisHelper().selectOne("selectAppuserByLogin", username);
		if( appuser == null ) {
			throw new UsernameNotFoundException("User not found");
		}
		return new MyUserDetails(appuser);
	}

}
