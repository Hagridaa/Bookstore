package viikko2.bookstore.Bookstoreprojekti.web;
//this class is used by spring security to authenticate ans authorize user

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import viikko2.bookstore.Bookstoreprojekti.domain.User;
import viikko2.bookstore.Bookstoreprojekti.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	 private final UserRepository repository;
	 
@Autowired public UserDetailServiceImpl(UserRepository userRepository) {
	    	this.repository = userRepository;

}
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	User curruser = repository.findByUsername(username);
	UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHass(),
			AuthorityUtils.createAuthorityList(curruser.getRole()));
	return user;
}
}
