package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/allusers")
    public List<User> getAllUsers(){return userService.findAll();}

    @GetMapping("/{email}")
    public User getUserById(@PathVariable String email){return userService.getById(email);}

    @PostMapping(value = {"","/Register"})
    public User createNewUser(@RequestBody User user) {return userService.addUser(user);}

    @PostMapping(value = {"","/login"})
    public User Login(@RequestBody User user) {return userService.loginUser(user.getEmail(), user.getPassword());}

    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable String email){userService.deleteUser(email);}

}
