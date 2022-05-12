package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("adminPanel/allusers")
    public List<User> getAllUsers(){return userService.findAll();}

    @GetMapping("adminPanel/{email}")
    public User getUserById(@PathVariable String email) {return userService.getById(email);}

    @DeleteMapping("adminPanel/{email}")
    public void deleteUser(@PathVariable String email){userService.deleteUser(email);}

    @PostMapping(value = {"","/Register"})
    public String createNewUser(@RequestBody User user) {return userService.addUser(user);}

    @PostMapping(value = {"","/login"})
    public String Login(@RequestBody User user) {return userService.loginUser(user);}



}
