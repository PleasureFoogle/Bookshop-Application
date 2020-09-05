package yesgroup.myapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yesgroup.myapplication.domain.MyUserDetails;
import yesgroup.myapplication.domain.entities.User;
import yesgroup.myapplication.domain.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        MyUserDetails myUserDetails = new MyUserDetails(user);
        return myUserDetails;
    }
}
