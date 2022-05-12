package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public String loginUser(User user)
    {
        passwordEncoder = new BCryptPasswordEncoder();
        User userInDataBase;
    if(!userRepository.findById(user.getEmail()).isEmpty())
    {
        userInDataBase= userRepository.findById(user.getEmail()).get();

        if(passwordEncoder.matches(user.getPassword(),userInDataBase.getPassword()))
        {
            userRepository.findByPassword(userInDataBase.getPassword()).getPassword();
//            return userRepository.getUserByemailAndpassword(user.getEmail(),userInDataBase.getPassword());
            return "Signed In Successfully";
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

    public String addUser(User user)
    {
        passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword= this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Optional<User> UserInDataBase;
        UserInDataBase=userRepository.findById(user.getEmail());
        if(UserInDataBase.isEmpty())
        {
            userRepository.insert(user);
            return "New User has been added to the database";

        }
        else{
            return "Already Registered";
            }
    }

    public void deleteUser(String email)
    {
        userRepository.deleteById(email);
    }
}
