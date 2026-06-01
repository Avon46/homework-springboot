package com.example.demo.entity;

import java.time.LocalDate;

import com.example.demo.enums.CustomerStatus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("customers")
public class Customer {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phone;
    private String email;
    @TableField("identity_number")
    private String identityNumber;
    private LocalDate birthday;
    private CustomerStatus status;

    public Customer() {
    }

    public Customer(Integer id, String name, String phone, String email, String identityNumber, LocalDate birthday,
            CustomerStatus status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.identityNumber = identityNumber;
        this.birthday = birthday;
        this.status = status;
    }
}