package com.example.demo.dto;

import java.time.LocalDate;

public class CreateCustomerRequest {
    private String name;
    private String phone;
    private String email;
    private String identityNumber;
    private LocalDate birthday;

    public CreateCustomerRequest() {
    }

    public CreateCustomerRequest(String name, String phone, String email, String identityNumber, LocalDate birthday) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.identityNumber = identityNumber;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}
