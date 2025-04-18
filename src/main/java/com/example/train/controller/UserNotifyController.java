package com.example.train.controller;

import com.example.train.bean.UserNotify;
import com.example.train.service.UserNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userNotify")
public class UserNotifyController {
    @Autowired
    private UserNotifyService userNotifyService;

    @GetMapping("/{idNotify}")
    public UserNotify getUserNotifyById(@PathVariable int idNotify) {
        return userNotifyService.getUserNotifyById(idNotify);
    }

    @PostMapping
    public void addUserNotify(@RequestBody UserNotify userNotify) {
        userNotifyService.addUserNotify(userNotify);
    }

    @PutMapping
    public void updateUserNotify(@RequestBody UserNotify userNotify) {
        userNotifyService.updateUserNotify(userNotify);
    }

    @DeleteMapping("/{idNotify}")
    public void deleteUserNotify(@PathVariable int idNotify) {
        userNotifyService.deleteUserNotify(idNotify);
    }
}
