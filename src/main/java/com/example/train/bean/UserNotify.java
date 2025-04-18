package com.example.train.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotify {
    private int IdNotify;
    private int IdUser;
    private String NotifyContent;
    private String Date;
}
