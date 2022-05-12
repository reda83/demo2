package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User loginUser(User user)
    {
        passwordEncoder = new BCryptPasswordEncoder();
        User userInDatabBase;
    if(!userRepository.findById(user.getEmail()).isEmpty())
    {
        userInDatabBase= userRepository.findById(user.getEmail()).get();

        if(passwordEncoder.matches(user.getPassword(),userInDatabBase.getPassword()))
        {
            userRepository.findByPassword(userInDatabBase.getPassword()).getPassword();
            return userRepository.getUserByemailAndpassword(user.getEmail(),userInDatabBase.getPassword());
        }else
        {
            throw new NotFoundException(String.format("Wrong Password"));
        }
    }else
    {
        throw new NotFoundException(String.format("no record with this email [%s] was found",user.getEmail()));
    }


    }
    public User getById(String email)
    {
    if(userRepository.findById(email).isPresent())
    {
        return userRepository.findById(email).get();
    }else
    {
        throw new NotFoundException(String.format("no record with this email [%s] was found",email));
    }


    }

    public User addUser(User user)
    {
        passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword= this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.insert(user);
    }

    public void deleteUser(String email)
    {
        userRepository.deleteById(email);
    }
}
