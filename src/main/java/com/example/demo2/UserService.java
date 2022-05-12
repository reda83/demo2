package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User loginUser(String email, String password)
    {
    try{   userRepository.findById(email).get();}
    catch (NoSuchElementException ex)
    {throw new NotFoundException(String.format("no record with this email [%s] was found",email));}

    try{userRepository.findByPassword(password).getPassword();}
    catch (Exception ex)
    {throw new NotFoundException(String.format("Wrong Password"));}

    return userRepository.getUserByemailAndpassword(email,password);
    }
    public User getById(String email)
    {

        try {
            return userRepository.findById(email).get();
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException(String.format("no record with this email [%s] was found",email));
        }
    }

    public User addUser(User user)
    {
        return userRepository.insert(user);
    }

    public void deleteUser(String email)
    {
        userRepository.deleteById(email);
    }
}
