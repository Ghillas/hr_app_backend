package com.hr.api.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hr.api.infrastructure.postgresadapter.dbuser.DBUser;
import com.hr.api.infrastructure.postgresadapter.dbuser.DBUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DBUserRepository dbUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        DBUser user = dbUserRepository.findByUsername(username);
        if (user == null) { // TODO : fix the case when the user is not in the database
            return new User(
                "",
                "",
                new ArrayList<>()
            );
        } else {
            return new User(
                user.getUsername(), 
                user.getPassword(), 
                getGrantedAuthorities(user.getRole())
            );
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

}
