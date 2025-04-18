package com.example.train.controller;

import com.example.train.bean.User;
import com.example.train.security.jwt.JwtUtil;
import com.example.train.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<User> getUserInfo(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByName(username);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable int idUser){
        User user=userService.getUserById(idUser);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token ){
        List<User> users=userService.getAllUsers();
        if(!users.isEmpty()){
            return ResponseEntity.ok(users);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/login/{account}/{password}")
    public ResponseEntity<?> login(@PathVariable String account,@PathVariable String password){
        User user = userService.getUserByAccount(account);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户不存在");
        }
        if (!user.getPassWord().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
        }
        String token = JwtUtil.generateToken(user.getNameUser());

        // 构造返回结果（隐藏敏感信息）
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user); // 使用DTO隐藏敏感字段
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User user1=userService.getUserByIdCard(user.getIdCard());
        if(user1==null) {
            userService.addUser(user);
            User user2 = userService.getUserByIdCard(user.getIdCard());
            String token = JwtUtil.generateToken(user2.getNameUser());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user2); // 使用DTO隐藏敏感字段
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User updateUser){
        User user=userService.getUserById(updateUser.getIdUser());
        if(user!=null){
            userService.updateUser(updateUser);
            return ResponseEntity.ok(updateUser);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> deleteUser(@PathVariable int idUser){
        User user=userService.getUserById(idUser);
        if(user!=null){
            userService.deleteUser(idUser);
            return ResponseEntity.ok("user deleted");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
