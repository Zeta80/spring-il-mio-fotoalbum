package com.learning.fotoalbum.security;

import com.learning.fotoalbum.model.User;
import com.learning.fotoalbum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override                                     //email
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if ( user.isPresent()){
            return new DatabaseUserDetails(user.get());
        }else{
            throw new UsernameNotFoundException("user with this username: " + username + " not found");
        }
     }
}
