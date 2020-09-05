package yesgroup.myapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yesgroup.myapplication.domain.entities.User;
import yesgroup.myapplication.domain.repositories.UserRepository;
import yesgroup.myapplication.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerNewUserAccount(String rawFormData) {
        String message = "Registration successful.";

           try{
               rawFormData = rawFormData.replace("user_name=", "");
               rawFormData = rawFormData.replace("user_email=", "");
               rawFormData = rawFormData.replace("user_password=", "");
               rawFormData = rawFormData.replace("submit=Submit", "");
               rawFormData = rawFormData.replace("&", " ");

               List<String> formData = new ArrayList<>(Arrays.asList(rawFormData.split(" ")));

               if (userRepository.findByEmail(formData.get(1)) == null) {
                   User user = new User();
                   String role = "ROLE_USER";
                   user.setUsername(formData.get(0));
                   user.setEmail(formData.get(1));
                   user.setPassword((formData.get(2)));
                   user.setRole(role);
                   userRepository.saveAndFlush(user);
               } else {
                   message = "User already exists.";
               }
           } catch (IndexOutOfBoundsException e){
               message = " Invalid data.";
           }

        return message;
    }
}
